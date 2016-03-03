package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionBroadcastFactory;
import br.com.braga.adb.ui.window.WindowListExtraDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListActionBroadcastCommand extends AbstractCommand{

    private WindowStrategy windowStrategy;

    public WindowListActionBroadcastCommand(Project project, WindowStrategy callback) {
        super(project);
        this.windowStrategy = callback;
    }

    @Override
    public void execCommand() {
        ActionBroadcastFactory factoryModel = new ActionBroadcastFactory();

        Project project = getProject();
        WindowListExtraDialog dialog = new WindowListExtraDialog( project , factoryModel.getListAction(), "Broadcast Actions");
        dialog.setCallback(windowStrategy);
        dialog.show();
    }
}
