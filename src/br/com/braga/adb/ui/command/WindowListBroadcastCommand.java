package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.ActionFactory;
import br.com.braga.adb.model.BroadcastActionFactory;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 01/03/16.
 */
public class WindowListBroadcastCommand extends WindowList {

    public static final String TITLE = "Broadcast Actions List";

    public WindowListBroadcastCommand(Project project, IDevice device) {
        super(project, device);
    }

    @Override
    protected ActionFactory getActionFactory() {
        return new BroadcastActionFactory();
    }

    @Override
    protected String getWindowTitle() {
        return TITLE;
    }
}
