package br.com.braga.adb.ui.adapter;

/**
 * Created by ericbraga on 01/03/16.
 */

import br.com.braga.adb.model.IntentFilterModel;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class WindowListModel implements ListModel<String> {

    private List<IntentFilterModel> elements;

    public WindowListModel(List<IntentFilterModel> elements) {
        this.elements = elements;
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public String getElementAt(int index) {
        return elements.get(index).getPresentationName();
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
