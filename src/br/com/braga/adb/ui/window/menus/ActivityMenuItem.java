package br.com.braga.adb.ui.window.menus;

import br.com.braga.adb.ui.listener.ActivityWindowListener;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 25/02/16.
 */
public class ActivityMenuItem extends MenuItem {
    public ActivityMenuItem( Project project ) {
        super("Activity", "activity.png", new ActivityWindowListener(project));
    }
}
