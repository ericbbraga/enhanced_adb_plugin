package br.com.braga.adb.ui.adapter;

import br.com.braga.adb.model.Extra;

import javax.swing.*;
import javax.swing.event.ListDataListener;

/**
 * Created by ericbraga on 13/06/16.
 */
public class ExtraColumnModel implements ComboBoxModel<Extra.ExtraType> {

    private Extra.ExtraType selected;

    private static final Extra.ExtraType[] types = Extra.ExtraType.values();

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (Extra.ExtraType) anItem;
    }

    @Override
    public Extra.ExtraType getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return types.length;
    }

    @Override
    public Extra.ExtraType getElementAt(int index) {
        return types[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
