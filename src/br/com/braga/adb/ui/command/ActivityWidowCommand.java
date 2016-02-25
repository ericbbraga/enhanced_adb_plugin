package br.com.braga.adb.ui.command;

import br.com.braga.adb.ui.window.ActivityWindowDialog;

import java.awt.*;

/**
 * Created by ericbraga on 25/02/16.
 */
public class ActivityWidowCommand implements Command {
    @Override
    public void execCommand() {
        Dialog dialog = new ActivityWindowDialog();
        dialog.setVisible(true);
    }
}
