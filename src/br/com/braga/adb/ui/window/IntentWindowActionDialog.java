package br.com.braga.adb.ui.window;

import br.com.braga.adb.model.Extra;
import br.com.braga.adb.model.IntentFilterModel;
import br.com.braga.adb.model.IntentString;
import br.com.braga.adb.model.IntentType;
import br.com.braga.adb.ui.adapter.AdbComboModel;
import br.com.braga.adb.ui.adapter.ExtraTableModel;
import br.com.braga.adb.ui.adapter.IntentTypeModel;
import br.com.braga.adb.ui.command.*;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.android.sdk.AndroidSdkUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntentWindowActionDialog extends DialogWrapper implements AndroidDebugBridge.IDeviceChangeListener, WindowExtraCallback, SendIntentCommand.SendIntentCallback {
    private final Project project;
    private JPanel contentPane;
    private JTable extraTable;
    private JList flagList;
    private JTextField actionTextField;
    private JButton listActionbutton;
    private JButton listCategoryButton;
    private JTextField categoryTextField;
    private JTextField componentTextField;
    private JButton button5;
    private JPanel panelMain;
    private JComboBox typeIntentCombobox;
    private JComboBox deviceCombobox;
    private JButton addButton;
    private JTextArea messageTextArea;
    private IntentFilterModel actionChosen;
    private IntentFilterModel categoryChoose;

    private List<IDevice> devices;
    private final AndroidDebugBridge bridge;
    private IDevice device;
    private IntentType typeChosen;

    public IntentWindowActionDialog(Project project) {
        super(project);
        this.project = project;

        devices = new ArrayList<>();
        bridge = AndroidSdkUtils.getDebugBridge(project);
        bridge.addDeviceChangeListener(this);

        updateDevices();
        init();
    }

    @Override
    protected void init() {
        super.init();

        setModal(true);
        setResizable(true);
        setTitle("Enhanced Adb");
        contentPane.setPreferredSize(new Dimension(640, 480));

        listActionbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowListAction();
            }
        });
        
        listCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWindowListCategory();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        typeIntentCombobox.setModel(new IntentTypeModel());
        typeIntentCombobox.setSelectedIndex(0);

        deviceCombobox.setModel(new AdbComboModel(devices));
        deviceCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deviceComboAction();
            }
        });

        extraTable.setModel(new ExtraTableModel());
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExtraWindowCommand(project, IntentWindowActionDialog.this).execCommand();
            }
        });
    }

    private void deviceComboAction() {
        int index = deviceCombobox.getSelectedIndex();
        if (index >= 0 && index < devices.size()) {
            device = devices.get(index);
        }
    }

    private void windowListAction() {
        IntentType intentType = (IntentType) typeIntentCombobox.getSelectedItem();
        switch (intentType) {
            case ACTIVITY:
                openWindowListAction();
                break;

            case BROADCAST:
                openWindowListBroadcastAction();
                break;

            case SERVICE:
                break;

            default:
                break;
        }
    }

    private void openWindowListAction() {
        WindowCallback strategy = new WindowCallback() {
            @Override
            public void handleReturn(IntentFilterModel element) {
                actionChosen = element;
                actionTextField.setText(element.getPresentationName());
            }
        };

        new WindowListActionCommand(project, strategy).execCommand();
    }

    private void openWindowListBroadcastAction() {

        WindowCallback strategy = new WindowCallback() {
            @Override
            public void handleReturn(IntentFilterModel element) {
                actionChosen = element;
                actionTextField.setText(element.getPresentationName());
            }
        };

        new WindowListActionBroadcastCommand(project, strategy).execCommand();
    }

    private void openWindowListCategory() {

        WindowCallback strategy = new WindowCallback() {
            @Override
            public void handleReturn(IntentFilterModel element) {
                categoryChoose = element;
                categoryTextField.setText(element.getPresentationName());
            }
        };

        new WindowListCategoryCommand(project, strategy).execCommand();
    }

    private void onOK() {

        if (device != null && device.isOnline()) {
            ExtraTableModel extraModel = (ExtraTableModel) extraTable.getModel();
            IntentType intentType = (IntentType) typeIntentCombobox.getSelectedItem();

            IntentString intentString = new IntentString(intentType);

            if (actionChosen != null) {
                intentString.setAction(actionChosen.getValue());
            }

            if (categoryChoose != null) {
                intentString.setCategory(categoryChoose.getValue());
            }

            intentString.setComponent(componentTextField.getText());
            intentString.setExtraComponent(extraModel);

            if (intentString.isActionConfigured()) {
                SendIntentCommand command = new SendIntentCommand(project, device, intentString.getFinalIntent());
                command.execCommand();

                resetMessageTextArea();
            } else {
                setMessageTextArea("Action should be configured");
            }

        } else {
            setMessageTextArea("Should select a online device");
        }
    }

    private void setMessageTextArea(String message) {
        resetMessageTextArea();
        messageTextArea.setText(message);
    }

    private void resetMessageTextArea() {
        messageTextArea.setText("");
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    public void updateDevices() {
        if (bridge != null) {
            devices.clear();
            devices.addAll( Arrays.asList(bridge.getDevices()) );
        }
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        DialogWrapperAction sendActionButton = new DialogWrapperAction("Send") {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                onOK();
            }
        };

        DialogWrapperAction cancelActionButton = new DialogWrapperAction("Cancel") {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                onCancel();
            }
        };

        return new Action[] { sendActionButton, cancelActionButton };
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

    @Override
    public void handleReturn(Extra extra) {
        ExtraTableModel extraModel = (ExtraTableModel) extraTable.getModel();
        if (extraModel != null && extra != null) {
            extraModel.addExtra(extra);
        }
    }

    @Override
    public void handleError(Exception e) {

    }

    @Override
    public void handleResult(boolean worked, String messageReturn) {
        if (worked) {
            dispose();

        } else {
            messageTextArea.append(messageReturn);
        }
    }
}
