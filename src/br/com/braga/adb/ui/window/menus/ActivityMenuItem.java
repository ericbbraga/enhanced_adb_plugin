package br.com.braga.adb.ui.window.menus;

import br.com.braga.adb.ui.listener.ActivityWindowListener;

/**
 * Created by ericbraga on 25/02/16.
 */
public class ActivityMenuItem extends MenuItem {
    public ActivityMenuItem() {
        super("Activity", "activity.png", new ActivityWindowListener());
    }
}
