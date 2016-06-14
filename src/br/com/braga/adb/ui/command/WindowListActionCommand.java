package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionFactory;
import br.com.braga.adb.ui.window.WindowActionsDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListActionCommand extends AbstractCommand {

    private WindowCallback windowCallback;

    public WindowListActionCommand(Project project, WindowCallback callback) {
        super(project);
        this.windowCallback = callback;
    }

    @Override
    public void execCommand() {
        ActionFactory factoryModel = new ActionFactory();

        Project project = getProject();
        WindowActionsDialog dialog = new WindowActionsDialog( project , factoryModel.getListAction(), "Intent Actions" );
        dialog.setCallback(windowCallback);
        dialog.show();
    }
}
