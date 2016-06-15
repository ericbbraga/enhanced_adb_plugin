package br.com.braga.adb.ui.window;

import br.com.braga.adb.model.Extra;
import br.com.braga.adb.model.FilterModel;
import br.com.braga.adb.model.IntentString;
import br.com.braga.adb.model.IntentType;
import br.com.braga.adb.ui.adapter.AdbComboModel;
import br.com.braga.adb.ui.adapter.ExtraTableModel;
import br.com.braga.adb.ui.adapter.IntentTypeModel;
import br.com.braga.adb.ui.adapter.WindowFlagsModel;
import br.com.braga.adb.ui.command.*;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.android.sdk.AndroidSdkUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntentWindowActionDialog extends DialogWrapper implements AndroidDebugBridge.IDeviceChangeListener, WindowExtraCallback, SendAdbCommand.SendIntentCallback, WindowListFlagsCallback {

    private static final Color NORMAL_MESSAGE_COLOR = Color.black;

    private static final Color ERROR_MESSAGE_COLOR  = Color.getHSBColor(107,32,143);

    private final Project project;
    private JPanel contentPane;
    private JTable extraTable;
    private JList flagList;
    private JTextField actionTextField;
    private JButton listActionbutton;
    private JButton listCategoryButton;
    private JTextField categoryTextField;
    private JTextField componentTextField;
    private JButton listComponentButton;
    private JPanel panelMain;
    private JComboBox typeIntentCombobox;
    private JComboBox deviceCombobox;
    private JButton addButton;
    private JTextArea messageTextArea;
    private JButton flagsButton;
    private FilterModel actionChosen;
    private FilterModel categoryChoose;

    private List<IDevice> devices;
    private final AndroidDebugBridge bridge;
    private IDevice device;
    private IntentType typeChosen;

    private List<FilterModel> flagsSelected;
    private WindowFlagsModel flagModel;

    public IntentWindowActionDialog(Project project) {
        super(project);
        this.project = project;

        devices = new ArrayList<>();
        flagsSelected = new ArrayList<>();
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

        listComponentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWindowListComponent();
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

        actionTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableComponentButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableComponentButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableComponentButton();
            }
        });

        flagsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWindowListFlags();
            }
        });

        flagModel = new WindowFlagsModel(flagsSelected);
        flagList.setModel(flagModel);
    }

    private void openWindowListFlags() {
        WindowListFlags window = new WindowListFlags(project, this);
        window.execCommand();
    }

    private void enableComponentButton() {
        boolean enable = !actionTextField.getText().isEmpty() && device != null;
        listComponentButton.setEnabled(enable);
    }

    private void deviceComboAction() {
        int index = deviceCombobox.getSelectedIndex();
        if (index >= 0 && index < devices.size()) {
            device = devices.get(index);
            enableComponentButton();
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
        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(FilterModel element) {
                actionChosen = element;
                actionTextField.setText(element.getPresentationName());
            }
        };

        new WindowListActionCommand(project, callback).execCommand();
    }

    private void openWindowListBroadcastAction() {

        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(FilterModel element) {
                actionChosen = element;
                actionTextField.setText(element.getPresentationName());
            }
        };

        new WindowListActionBroadcastCommand(project, callback).execCommand();
    }

    private void openWindowListCategory() {

        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(FilterModel element) {
                categoryChoose = element;
                categoryTextField.setText(element.getPresentationName());
            }
        };

        new WindowListCategoryCommand(project, callback).execCommand();
    }

    private void openWindowListComponent() {

        WindowComponentCallback callback = new WindowComponentCallback() {
            @Override
            public void handleReturn(String componentName) {
                componentTextField.setText(componentName);
            }
        };

        new WindowListComponent(project, device, actionChosen.getValue(), callback).execCommand();
    }

    private void onOK() {

        if (device != null && device.isOnline()) {
            ExtraTableModel extraModel = (ExtraTableModel) extraTable.getModel();
            IntentType intentType = (IntentType) typeIntentCombobox.getSelectedItem();

            IntentString intentString = new IntentString(intentType);
            intentString.setAction(actionTextField.getText());
            intentString.setCategory(categoryTextField.getText());
            intentString.setComponent(componentTextField.getText());

            intentString.setExtraComponent(extraModel);

            if (intentString.isActionConfigured()) {
                SendAdbCommand command = new SendAdbCommand(project, device, intentString.getFinalIntent());
                command.setCallback(this);
                command.execCommand();
            } else {
                setMessageTextArea("Action should be configured");
            }

        } else {
            setMessageTextArea("Should select a online device");
        }
    }

    private void setMessageTextArea(String message) {
        appendStringIntoMessageTextArea(message);
    }

    private void resetMessageTextArea() {
        messageTextArea.setText("");
    }

    private void onCancel() {
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
    public void handleResult(boolean worked, String[] messageReturn) {

        changeLogMessage(worked);

        for (String line : messageReturn) {
            appendStringIntoMessageTextArea(line);
        }
    }

    private void appendStringIntoMessageTextArea(String line) {
        messageTextArea.append(String.format("%s\n", line));
    }

    private void changeLogMessage(boolean worked) {
        Color color = worked ? NORMAL_MESSAGE_COLOR : ERROR_MESSAGE_COLOR;
        messageTextArea.setForeground(color);
    }

    @Override
    public void onFinishedProcess() {

    }

    @Override
    public void handleReturn(List<FilterModel> flags) {
        if (flags != null && flags.size() > 0) {
            flagsSelected.clear();
            flagsSelected.addAll(flags);

            flagModel.removeAll();
            flagModel.addElements(flagsSelected);
            flagList.updateUI();
        }
    }
}
