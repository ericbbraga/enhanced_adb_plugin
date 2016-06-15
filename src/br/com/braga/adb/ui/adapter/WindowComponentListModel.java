package br.com.braga.adb.ui.adapter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 14/06/16.
 */
public class WindowComponentListModel extends AbstractListModel<String> {

    private List<String> components;

    public WindowComponentListModel(List<String> components) {
        this.components = new ArrayList<>(components);
    }

    @Override
    public int getSize() {
        return components.size();
    }

    @Override
    public String getElementAt(int index) {
        return components.get(index);
    }

    public void update(List<String> componentsUpdated) {
        this.components = new ArrayList<>(componentsUpdated);
        this.fireContentsChanged(this, 0, this.components.size());
    }
}
