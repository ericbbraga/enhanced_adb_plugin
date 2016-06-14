package br.com.braga.adb.ui.adapter;

import br.com.braga.adb.model.Extra;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 13/06/16.
 */
public class ExtraTableModel extends DefaultTableModel {

    static final int TYPE_COLUMN_INDEX = 0;
    static final int NAME_COLUMN_INDEX = 1;
    static final int VALUE_COLUMN_INDEX = 2;

    private List<String> columns;

    private List<Extra> extras;

    public ExtraTableModel() {
        super();

        columns = new ArrayList<>();
        extras = new ArrayList<>();

        columns.add("Type");
        columns.add("Name");
        columns.add("Value");
    }

    public void addExtra(Extra extra) {
        extras.add(extra);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (extras == null) {
            return 0;
        }

        return extras.size();
    }

    @Override
    public int getColumnCount() {
        if (columns == null) {
            return 0;
        }
        return columns.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Extra extra = extras.get(rowIndex);

        switch (columnIndex) {
            case TYPE_COLUMN_INDEX:
                return extra.getType();
            case NAME_COLUMN_INDEX:
                return extra.getKey();
            case VALUE_COLUMN_INDEX:
                return extra.getValue();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }

    public List<Extra> getExtras() {
        return extras;
    }
}
