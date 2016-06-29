package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionFactory;
import br.com.braga.adb.model.ActivityActionFactory;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListIntentCommand extends WindowList {

    public static final String TITLE = "Action Intent List";

    public WindowListIntentCommand(Project project, IDevice device) {
        super(project, device);
    }

    @Override
    protected ActionFactory getActionFactory() {
        return new ActivityActionFactory();
    }

    @Override
    protected String getWindowTitle() {
        return TITLE;
    }
}
