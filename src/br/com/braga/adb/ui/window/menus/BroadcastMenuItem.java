package br.com.braga.adb.ui.window.menus;

import br.com.braga.adb.ui.listener.BroadcastWindowListener;
import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 25/02/16.
 */
public class BroadcastMenuItem extends MenuItem {
    public BroadcastMenuItem( Project project ) {
        super("broadcast", "broadcast.png", new BroadcastWindowListener( project ));
    }
}
