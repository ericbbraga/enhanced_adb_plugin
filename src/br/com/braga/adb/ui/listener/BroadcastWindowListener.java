package br.com.braga.adb.ui.listener;

import br.com.braga.adb.ui.command.ActivityWidowCommand;

/**
 * Created by ericbraga on 25/02/16.
 */
public class BroadcastWindowListener extends MenuActionListener{
    public BroadcastWindowListener() {
        super( new ActivityWidowCommand());
    }
}
