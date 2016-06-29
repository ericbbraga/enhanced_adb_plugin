package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionFactory;
import br.com.braga.adb.model.ActionFactoryCallback;
import br.com.braga.adb.ui.window.WindowComponent;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;

import java.util.List;

/**
 * Created by ericbraga on 29/06/16.
 */
public abstract class WindowList extends AbstractCommand implements ActionFactoryCallback {
    private IDevice device;
    private WindowCallback windowCallback;
    private ActionFactory factoryModel;

    public WindowList(Project project, IDevice device) {
        super(project);
        this.device = device;
    }

    public void setWindowCallback(WindowCallback callback) {
        this.windowCallback = callback;
    }

    @Override
    public void execCommand() {
        Project project = getProject();

        factoryModel = getActionFactory();
        factoryModel.setCallback(this);
        factoryModel.loadList(project, device);
    }

    protected abstract ActionFactory getActionFactory();

    @Override
    public void handleError(Exception e) {
        //TODO handle the exception
    }

    @Override
    public void onFinishedProcess(List<String> actions) {
        Project project = getProject();
        String title = getWindowTitle();
        WindowComponent dialog = new WindowComponent(project, actions, title);

        dialog.setCallback(windowCallback);
        dialog.show();
    }

    protected abstract String getWindowTitle();
}
