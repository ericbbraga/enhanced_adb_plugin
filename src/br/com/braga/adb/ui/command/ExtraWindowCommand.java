package br.com.braga.adb.ui.command;

import br.com.braga.adb.ui.window.ExtraWindowDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 13/06/16.
 */
public class ExtraWindowCommand extends AbstractCommand {
    private WindowExtraCallback callback;

    public ExtraWindowCommand(Project project, WindowExtraCallback callback) {
        super(project);
        this.callback = callback;
    }

    @Override
    public void execCommand() {
        Project project = getProject();
        ExtraWindowDialog extraWindowDialog = new ExtraWindowDialog(project, callback);
        extraWindowDialog.show();
    }
}
