package br.com.braga.adb.ui.window;

import br.com.braga.adb.ui.PopupMenuFactory;
import br.com.braga.adb.ui.adapter.AdbTableModel;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import org.jetbrains.android.sdk.AndroidSdkUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ericbraga on 23/02/16.
 */
public class AdbWindowPanel extends SimpleToolWindowPanel implements ActionListener, AndroidDebugBridge.IDeviceChangeListener {

    private JPanel panel1;

    private JTable table1;

    private Project project;

    private List<IDevice> devices;

    private PopupMenuFactory popUpFactory;
    private final AndroidDebugBridge bridge;

    public AdbWindowPanel(Project project) {
        super(true, true);
        setContent(panel1);

        this.project = project;
        this.devices = new ArrayList<>();

        AdbTableModel model = new AdbTableModel(devices);
        table1.setModel(model);

        popUpFactory = new PopupMenuFactory( project );

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.isMetaDown()) { // Right Click
                    showMenuPopup(e);
                }
            }
        });

        bridge = AndroidSdkUtils.getDebugBridge(project);
        bridge.addDeviceChangeListener(this);
    }

    private void showMenuPopup(MouseEvent e) {
        JPopupMenu popup = popUpFactory.createJPopupMenu();
        popup.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("button")) {
            Messages.showDialog("ok", "Document Text", new String[] {"OK"}, -1, null);
        }
    }

    public void updateDevices() {
        if (bridge != null) {
            devices.clear();
            devices.addAll( Arrays.asList(bridge.getDevices()) );
        }
    }

    @Override
    public void deviceConnected(IDevice iDevice) {
        updateDevices();
    }

    @Override
    public void deviceDisconnected(IDevice iDevice) {
        updateDevices();
    }

    @Override
    public void deviceChanged(IDevice iDevice, int i) {
        updateDevices();
    }
}
