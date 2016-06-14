package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.CategoryFactory;
import br.com.braga.adb.ui.window.WindowActionsDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListCategoryCommand extends AbstractCommand {

    private WindowCallback windowCallback;

    public WindowListCategoryCommand(Project project, WindowCallback callback) {
        super(project);
        this.windowCallback = callback;
    }

    @Override
    public void execCommand() {
        CategoryFactory factoryModel = new CategoryFactory();

        Project project = getProject();
        WindowActionsDialog dialog = new WindowActionsDialog( project , factoryModel.getList(), "Category List" );
        dialog.setCallback(windowCallback);
        dialog.show();
    }
}
