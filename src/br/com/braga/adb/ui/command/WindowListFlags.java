package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.FlagsFactory;
import br.com.braga.adb.ui.window.WindowFlagsDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 15/06/16.
 */
public class WindowListFlags extends AbstractCommand {

    private WindowListFlagsCallback windowListFlagsCallback;

    public WindowListFlags(Project project, WindowListFlagsCallback windowListFlagsCallback) {
        super(project);
        this.windowListFlagsCallback = windowListFlagsCallback;
    }

    @Override
    public void execCommand() {
        FlagsFactory factory = new FlagsFactory();
        WindowFlagsDialog dialog = new WindowFlagsDialog(getProject(), factory.getList(), "Flag List");
        dialog.setCallback(windowListFlagsCallback);
        dialog.show();
    }
}
