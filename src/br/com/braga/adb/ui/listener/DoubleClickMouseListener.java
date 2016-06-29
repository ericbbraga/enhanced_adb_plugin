package br.com.braga.adb.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ericbraga on 29/06/16.
 */
public abstract class DoubleClickMouseListener implements MouseListener {

    private final int CLICK_COUNT_TRIGGER = 2;

    public abstract void triggerEvent();

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == CLICK_COUNT_TRIGGER) {
            triggerEvent();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // We only use the mouse Click event
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // We only use the mouse Click event
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // We only use the mouse Click event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // We only use the mouse Click event
    }
}
