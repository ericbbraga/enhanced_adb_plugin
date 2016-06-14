package br.com.braga.adb.model;

import br.com.braga.adb.ui.adapter.ExtraTableModel;

import java.util.List;

/**
 * Created by ericbraga on 13/06/16.
 */
public class IntentString {

    private static final String ADB_INTENT = "am %s %s %s %s %s";

    private IntentType type;

    private String action;

    private String category;

    private String component;

    private ExtraTableModel extraComponent;

    private boolean actionConfigured = false;

    public IntentString(IntentType intentType) {
        type = intentType;
        action = "";
        category = "";
        component = "";
    }

    public void setAction(String action) {
        if (action != null && !action.isEmpty()) {
            this.action = String.format("-a %s", action);
            actionConfigured = true;

        } else {
            actionConfigured = false;
        }
    }

    public void setCategory(String category) {
        if (category != null && !category.isEmpty()) {
            this.category = String.format("-c %s", category);
        }
    }

    public void setComponent(String component) {
        if (component != null && !component.isEmpty()) {
            this.component = String.format("-n %s", component);
        }
    }

    public boolean isActionConfigured() {
        return actionConfigured;
    }

    public String getFinalIntent() {

        String intent = null;

        switch (type) {
            case ACTIVITY:
                intent = "start";
                break;
            case BROADCAST:
                intent = "broadcast";
                break;
            case SERVICE:
                intent = "startservice";
                break;
        }

        String extraParams = getExtraParams();

        return String.format(ADB_INTENT, intent, action, category, component, extraParams);
    }

    public void setExtraComponent(ExtraTableModel extraComponent) {
        this.extraComponent = extraComponent;
    }

    public String getExtraParams() {

        StringBuilder builder = new StringBuilder();

        if (extraComponent != null) {
            List<Extra> extras = extraComponent.getExtras();

            for (int i = 0; i < extras.size(); i++) {
                Extra extra = extras.get(i);


                Extra.ExtraType extraType = extra.getType();
                String key = extra.getKey();
                String value = extra.getValue();

                if (extraType == null || (key == null || key.isEmpty()) || (value == null || value.isEmpty())) {
                    continue;
                }

                builder.append(String.format("%s \"%s\" \"%s\" ", extraType.getExtraParameter(), key, value));
            }
        }

        return builder.toString();
    }
}
