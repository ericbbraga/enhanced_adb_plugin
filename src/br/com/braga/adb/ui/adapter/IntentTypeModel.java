package br.com.braga.adb.ui.adapter;

import br.com.braga.adb.model.IntentType;

import javax.swing.*;
import javax.swing.event.ListDataListener;

/**
 * Created by ericbraga on 01/03/16.
 */
public class IntentTypeModel extends AbstractListModel<IntentType> implements ComboBoxModel<IntentType> {
    private Object selectedItem;

    @Override
    public int getSize() {
        return IntentType.values().length;
    }

    @Override
    public IntentType getElementAt(int index) {
        return IntentType.values()[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selectedItem = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
