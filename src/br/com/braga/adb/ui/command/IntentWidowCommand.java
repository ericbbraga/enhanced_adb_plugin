package br.com.braga.adb.ui.command;

import br.com.braga.adb.ui.window.IntentWindowActionDialog;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 25/02/16.
 */
public class IntentWidowCommand extends AbstractCommand {
    public IntentWidowCommand(Project project) {
        super(project);
    }

    @Override
    public void execCommand() {
        Project project = getProject();

        IntentWindowActionDialog dialog = new IntentWindowActionDialog( project );
        dialog.show();
    }
}
