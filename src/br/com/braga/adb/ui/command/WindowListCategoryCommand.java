package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.CategoryFactory;
import br.com.braga.adb.ui.window.WindowListExtraDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListCategoryCommand extends AbstractCommand {

    private WindowStrategy windowStrategy;

    public WindowListCategoryCommand(Project project, WindowStrategy callback) {
        super(project);
        this.windowStrategy = callback;
    }

    @Override
    public void execCommand() {
        CategoryFactory factoryModel = new CategoryFactory();

        Project project = getProject();
        WindowListExtraDialog dialog = new WindowListExtraDialog( project , factoryModel.getList(), "Category List" );
        dialog.setCallback(windowStrategy);
        dialog.show();
    }
}
