package br.com.braga.adb.ui.window.menus;

import br.com.braga.adb.ui.listener.BroadcastWindowListener;

/**
 * Created by ericbraga on 25/02/16.
 */
public class BroadcastMenuItem extends MenuItem {
    public BroadcastMenuItem() {
        super("broadcast", "broadcast.png", new BroadcastWindowListener());
    }
}
