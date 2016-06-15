package br.com.braga.adb.ui.adapter;

import br.com.braga.adb.model.FilterModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 15/06/16.
 */
public class WindowFlagsModel extends AbstractListModel<String> {

    private List<FilterModel> flags;

    public WindowFlagsModel(List<FilterModel> flags) {
        this.flags = new ArrayList<>(flags);
    }

    @Override
    public int getSize() {
        return flags.size();
    }

    @Override
    public String getElementAt(int index) {
        return flags.get(index).getPresentationName();
    }

    public void addElement(FilterModel flag) {
        flags.add(flag);
    }

    public void addElements(List<FilterModel> flags) {
        this.flags.addAll(flags);
        fireContentsChanged(this, 0, flags.size());
    }

    public void removeAll() {
        this.flags.clear();
    }
}
