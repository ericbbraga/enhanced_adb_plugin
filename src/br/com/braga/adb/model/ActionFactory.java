package br.com.braga.adb.model;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {

    private List<IntentFilterModel> actions;

    public ActionFactory() {
        actions = new ArrayList<>();

        actions.add(new IntentFilterModel("android.intent.action.MAIN", "Intent.ACTION_MAIN"));
        actions.add(new IntentFilterModel("android.intent.action.VIEW", "Intent.ACTION_VIEW"));
        actions.add(new IntentFilterModel("android.intent.action.ATTACH_DATA", "Intent.ACTION_ATTACH_DATA"));
        actions.add(new IntentFilterModel("android.intent.action.EDIT", "Intent.ACTION_EDIT"));
        actions.add(new IntentFilterModel("android.intent.action.PICK", "Intent.ACTION_PICK"));
        actions.add(new IntentFilterModel("android.intent.action.CHOOSER", "Intent.ACTION_CHOOSER"));
        actions.add(new IntentFilterModel("android.intent.action.GET_CONTENT", "Intent.ACTION_GET_CONTENT"));
        actions.add(new IntentFilterModel("android.intent.action.DIAL", "Intent.ACTION_DIAL"));
        actions.add(new IntentFilterModel("android.intent.action.CALL", "Intent.ACTION_CALL"));
        actions.add(new IntentFilterModel("android.intent.action.SEND", "Intent.ACTION_SEND"));
        actions.add(new IntentFilterModel("android.intent.action.SENDTO", "Intent.ACTION_SENDTO"));
        actions.add(new IntentFilterModel("android.intent.action.ANSWER", "Intent.ACTION_ANSWER"));
        actions.add(new IntentFilterModel("android.intent.action.INSERT", "Intent.ACTION_INSERT"));
        actions.add(new IntentFilterModel("android.intent.action.DELETE", "Intent.ACTION_DELETE"));
        actions.add(new IntentFilterModel("android.intent.action.RUN", "Intent.ACTION_RUN"));
        actions.add(new IntentFilterModel("android.intent.action.SYNC", "Intent.ACTION_SYNC"));
        actions.add(new IntentFilterModel("android.intent.action.PICK_ACTIVITY", "Intent.ACTION_PICK_ACTIVITY"));
        actions.add(new IntentFilterModel("android.intent.action.SEARCH", "Intent.ACTION_SEARCH"));
        actions.add(new IntentFilterModel("android.intent.action.WEB_SEARCH", "Intent.ACTION_WEB_SEARCH"));
        actions.add(new IntentFilterModel("android.intent.action.FACTORY_TEST", "Intent.ACTION_FACTORY_TEST"));
    }

    public List<IntentFilterModel> getListAction() {
        ArrayList<IntentFilterModel> actionsCopy = new ArrayList<>();
        actionsCopy.addAll(actions);

        return actionsCopy;
    }
}
