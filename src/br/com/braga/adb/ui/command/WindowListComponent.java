package br.com.braga.adb.ui.command;

import br.com.braga.adb.ui.window.WindowComponent;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 14/06/16.
 */
public class WindowListComponent extends AbstractCommand implements SendAdbCommand.SendIntentCallback {

    private static final int CLASS_COMPONENT_INDEX = 1;

    private static final String REGEX_COMPONENT_SPLIT = " ";

    private static final String COMPONENT_ADB = "dumpsys package r";

    private WindowCallback callback;

    private String action;

    private IDevice device;

    private boolean foundAction;

    List<String> components;

    public WindowListComponent(Project project, IDevice device, String action) {
        super(project);
        this.device = device;
        this.action = action;
        foundAction = false;
        components = new ArrayList<>();

    }

    @Override
    public void execCommand() {
        loadComponentsForAction();
    }

    private void loadComponentsForAction() {
        SendAdbCommand command = new SendAdbCommand(getProject(), this.device, COMPONENT_ADB);
        command.setCallback(this);
        command.execCommand();
    }

    @Override
    public void handleError(Exception e) {

    }

    @Override
    public void handleResult(boolean worked, String[] messageReturn) {
        if (worked) {
            for (String lines : messageReturn) {
                if (lines.contains(this.action)) {
                    foundAction = true;
                    continue;
                }

                if (foundAction) {
                    if (lines.contains(":")) {
                        foundAction = false;
                        break;
                    }

                    String[] splitLine = lines.split(REGEX_COMPONENT_SPLIT);

                    if (splitLine.length > 1) {
                        components.add(splitLine[CLASS_COMPONENT_INDEX]);
                    }
                }
            }

        }
    }

    @Override
    public void onFinishedProcess() {
        Project project = getProject();

        WindowComponent dialog = new WindowComponent(project , components, String.format("Component List for %s", action));
        dialog.setCallback(callback);
        dialog.show();
    }

    public void setCallback(WindowCallback callback) {
        this.callback = callback;
    }
}