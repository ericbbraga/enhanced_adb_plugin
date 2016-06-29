package br.com.braga.adb.ui.listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by ericbraga on 29/06/16.
 */
public abstract class TextFieldChangeListener implements DocumentListener {

    public abstract void triggerEvent();

    @Override
    public final void insertUpdate(DocumentEvent e) {
        triggerEvent();
    }

    @Override
    public final void removeUpdate(DocumentEvent e) {
        triggerEvent();
    }

    @Override
    public final void changedUpdate(DocumentEvent e) {
        triggerEvent();
    }
}
