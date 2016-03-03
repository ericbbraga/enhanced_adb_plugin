package br.com.braga.adb.ui.adapter;

import com.android.ddmlib.IDevice;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 24/02/16.
 */
public class AdbTableModel extends AbstractTableModel {

    public static final int SERIAL_NUMBER = 0;
    private List<String> columns;

    private List<IDevice> devices;

    public AdbTableModel(List<IDevice> deviceList) {
        columns = new ArrayList<>();
        columns.add("Serial");
        columns.add("Status");

        devices = deviceList;
    }

    @Override
    public int getRowCount() {
        return devices.size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == SERIAL_NUMBER) {
            return devices.get(rowIndex).getSerialNumber();
        } else {
            return devices.get(rowIndex).getState();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
