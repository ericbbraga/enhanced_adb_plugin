package br.com.braga.adb.ui.listener;

import br.com.braga.adb.ui.command.IntentWidowCommand;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 25/02/16.
 */
public class ActivityWindowListener extends MenuActionListener {
    public ActivityWindowListener(Project project) {
        super( new IntentWidowCommand( project ) );
    }
}
