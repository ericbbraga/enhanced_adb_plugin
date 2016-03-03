package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionFactory;
import br.com.braga.adb.ui.window.WindowListExtraDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListActionCommand extends AbstractCommand {

    private WindowStrategy windowStrategy;

    public WindowListActionCommand(Project project, WindowStrategy callback) {
        super(project);
        this.windowStrategy = callback;
    }

    @Override
    public void execCommand() {
        ActionFactory factoryModel = new ActionFactory();

        Project project = getProject();
        WindowListExtraDialog dialog = new WindowListExtraDialog( project , factoryModel.getListAction(), "Intent Actions" );
        dialog.setCallback(windowStrategy);
        dialog.show();
    }
}
