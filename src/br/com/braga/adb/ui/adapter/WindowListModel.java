package br.com.braga.adb.ui.adapter;

/**
 * Created by ericbraga on 01/03/16.
 */

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class WindowListModel extends AbstractListModel<String> {

    private List<String> elements;

    public WindowListModel(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public String getElementAt(int index) {
        return elements.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
