package br.com.braga.adb;

import com.android.ddmlib.IDevice;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.SimpleToolWindowPanel;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 23/02/16.
 */
public class AdbViewer extends SimpleToolWindowPanel implements ActionListener{

    private List<IDevice> devices;

    private JPanel panel1;

    private JTable table1;

    public AdbViewer() {
        super(true);
        setContent(panel1);

        AdbTableModel model = new AdbTableModel();
        table1.setModel(model);
        table1.setTableHeader(new JTableHeader());

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.isMetaDown()) { // Right Click
                    showMenuPopup(e);
                }
            }
        });

        devices = new ArrayList<>();
    }

    private void showMenuPopup(MouseEvent e) {
        JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem("connect with"));
        popup.setSize(350, 10);
        popup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void setDevices(List<IDevice> devices) {
        this.devices = devices;
        table1.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("button")) {
            Messages.showDialog("ok", "Document Text", new String[] {"OK"}, -1, null);
        }
    }

    private class AdbTableModel extends AbstractTableModel {

        public AdbTableModel() {
            super();
        }

        @Override
        public int getRowCount() {
            return devices.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return "Name";
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
            return devices.get(rowIndex).getSerialNumber();
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
}
