package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionFactory;
import br.com.braga.adb.model.ServiceActionFactory;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 29/06/16.
 */
public class WindowListServiceCommand extends WindowList {

    public static final String TITLE = "Service Action List";

    public WindowListServiceCommand(Project project, IDevice device) {
        super(project, device);
    }

    @Override
    protected ActionFactory getActionFactory() {
        return new ServiceActionFactory();
    }

    @Override
    protected String getWindowTitle() {
        return TITLE;
    }
}
