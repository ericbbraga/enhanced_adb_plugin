package br.com.braga.adb.ui.adapter;

import com.android.ddmlib.IDevice;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 24/02/16.
 */
public class AdbComboModel extends AbstractListModel implements ComboBoxModel {

    private String selectedItem;

    private List<IDevice> devices = new ArrayList<>();

    public AdbComboModel(List<IDevice> deviceList) {
        devices.addAll(deviceList);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (String) anItem;
    }

    @Override
    public String getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return devices.size();
    }

    @Override
    public String getElementAt(int index) {
        return devices.get(index).getName();
    }
}
