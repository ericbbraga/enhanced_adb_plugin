package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionBroadcastFactory;
import br.com.braga.adb.ui.window.WindowActionsDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListActionBroadcastCommand extends AbstractCommand{

    private WindowCallback windowCallback;

    public WindowListActionBroadcastCommand(Project project, WindowCallback callback) {
        super(project);
        this.windowCallback = callback;
    }

    @Override
    public void execCommand() {
        ActionBroadcastFactory factoryModel = new ActionBroadcastFactory();

        Project project = getProject();
        WindowActionsDialog dialog = new WindowActionsDialog( project , factoryModel.getListAction(), "Broadcast Actions");
        dialog.setCallback(windowCallback);
        dialog.show();
    }
}
