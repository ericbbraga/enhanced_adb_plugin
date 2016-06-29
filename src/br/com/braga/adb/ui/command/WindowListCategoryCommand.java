package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.CategoryFactory;
import br.com.braga.adb.ui.window.WindowComponent;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListCategoryCommand extends AbstractCommand {

    private WindowCallback windowCallback;

    public WindowListCategoryCommand(Project project) {
        super(project);
    }

    public void setWindowCallback(WindowCallback callback) {
        this.windowCallback = callback;
    }

    @Override
    public void execCommand() {
        CategoryFactory factoryModel = new CategoryFactory();

        Project project = getProject();
        WindowComponent dialog = new WindowComponent( project , factoryModel.getList(), "Category List" );
        dialog.setCallback(windowCallback);
        dialog.show();
    }
}
