package br.com.braga.adb.model;

import br.com.braga.adb.ui.command.SendAdbCommand;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

public abstract class ActionFactory implements SendAdbCommand.SendIntentCallback {

    private static final String GET_ALL_ACTIVITIES_COMMAND = "dumpsys package r";
    private static final String DELIMITER_ACTIONS = "Non-Data Actions";
    private static final String DELIMITER_END_ACTIONS = "MIME Typed Actions";

    private List<String> actions;
    private boolean foundGroup;
    private boolean foundActions;
    private boolean finishSearch;

    private ActionFactoryCallback callback;


    public ActionFactory() {
        actions = new ArrayList<>();
        foundGroup = false;
        foundActions = false;
        finishSearch = false;
    }

    public void loadList(Project project, IDevice device) {
        SendAdbCommand command = new SendAdbCommand(project, device, GET_ALL_ACTIVITIES_COMMAND);
        command.setCallback(this);
        command.execCommand();
    }

    public void setCallback(ActionFactoryCallback callback) {
        this.callback = callback;
    }

    @Override
    public void handleError(Exception e) {
        if (callback != null) {
            callback.handleError(e);
        }
    }

    @Override
    public void handleResult(boolean worked, String[] messageReturn) {
        if (worked && !finishSearch) {

            for (String lines : messageReturn) {
                if (lines.contains(getDelimiterGroupAction())) {
                    foundGroup = true;
                    continue;
                }

                if (foundGroup) {
                    if (lines.contains(DELIMITER_ACTIONS)) {
                        foundActions = true;
                        continue;
                    }

                    if (lines.contains(DELIMITER_END_ACTIONS)) {
                        finishSearch = true;
                        break;
                    }

                    if (foundActions) {
                        if (lines.contains(":")) {
                            String activityAction = lines.replace(":", "");
                            actions.add(activityAction);
                        }
                    }
                }
            }

        }
    }

    protected abstract String getDelimiterGroupAction();

    @Override
    public void onFinishedProcess() {
        if (callback != null) {
            callback.onFinishedProcess( new ArrayList<>(actions) );
        }
    }
}
