package br.com.braga.adb.ui.adapter;

/**
 * Created by ericbraga on 01/03/16.
 */

import br.com.braga.adb.model.FilterModel;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class WindowListModel extends AbstractListModel<String> {

    private List<FilterModel> elements;

    public WindowListModel(List<FilterModel> elements) {
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
