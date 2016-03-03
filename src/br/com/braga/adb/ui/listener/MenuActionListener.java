package br.com.braga.adb.ui.listener;

import br.com.braga.adb.ui.command.Command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ericbraga on 25/02/16.
 */
public abstract class MenuActionListener implements ActionListener {

    private Command currentCommand;

    public MenuActionListener(Command command) {
        setCommand(command);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentCommand.execCommand();
    }

    public void setCommand(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("The command could not be null");
        }

        this.currentCommand = command;
    }
}
