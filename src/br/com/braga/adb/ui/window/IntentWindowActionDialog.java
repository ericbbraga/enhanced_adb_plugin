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
import br.com.braga.adb.ui.listener.TextFieldChangeListener;
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

public class IntentWindowActionDialog extends DialogWrapper implements AndroidDebugBridge.IDeviceChangeListener, WindowExtraCallback{

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
    private JButton flagsButton;
    private JButton clearLogButton;
    private JTextArea logTextArea;
    private JButton sendIntentButton;

    private List<IDevice> devices;
    private final AndroidDebugBridge bridge;
    private IDevice device;
    private IntentType typeChosen;

    private List<FilterModel> flagsSelected;
    private WindowFlagsModel flagModel;

    private AdbComboModel comboboxModel;

    public IntentWindowActionDialog(Project project) {
        super(project);
        this.project = project;

        devices = new ArrayList<>();
        flagsSelected = new ArrayList<>();
        bridge = AndroidSdkUtils.getDebugBridge(project);
        bridge.addDeviceChangeListener(this);

        init();
        updateDevices();
    }

    @Override
    protected void init() {
        super.init();

        setModal(true);
        setResizable(true);
        setTitle("Enhanced Adb");


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        contentPane.setPreferredSize(new Dimension(640, screenSize.height));

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

        comboboxModel = new AdbComboModel(devices);

        deviceCombobox.setModel(comboboxModel);
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

        actionTextField.getDocument().addDocumentListener(new TextFieldChangeListener() {
            @Override
            public void triggerEvent() {
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

        clearLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMessageTextArea();
            }
        });

        sendIntentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendIntent();
            }
        });
    }

    private void openWindowListFlags() {

        WindowListFlagsCallback callback = new WindowListFlagsCallback() {
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
        };

        WindowListFlags window = new WindowListFlags(project, callback);
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
            enableActionButton();
            enableCategoryButton();

        } else {
            disableActionButton();
            disableCategoryButton();
        }
    }

    private void disableActionButton() {
        listActionbutton.setEnabled(false);
    }

    private void disableCategoryButton() {
        listCategoryButton.setEnabled(false);
    }

    private void enableActionButton() {
        listActionbutton.setEnabled(true);
    }

    private void enableCategoryButton() {
        listCategoryButton.setEnabled(true);
    }

    private void windowListAction() {
        IntentType intentType = (IntentType) typeIntentCombobox.getSelectedItem();
        switch (intentType) {
            case ACTIVITY:
                openWindowListActivity();
                break;

            case BROADCAST:
                openWindowListBroadcast();
                break;

            case SERVICE:
                openWindowServiceList();
                break;

            default:
                break;
        }
    }

    private void openWindowServiceList() {
        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(String element) {
                actionTextField.setText(element);
            }
        };

        WindowListServiceCommand windowListServiceCommand = new WindowListServiceCommand(project, device);
        windowListServiceCommand.setWindowCallback(callback);
        windowListServiceCommand.execCommand();
    }

    private void openWindowListActivity() {
        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(String element) {
                actionTextField.setText(element);
            }
        };

        WindowListIntentCommand windowListAction = new WindowListIntentCommand(project, device);
        windowListAction.setWindowCallback(callback);
        windowListAction.execCommand();
    }

    private void openWindowListBroadcast() {

        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(String element) {
                actionTextField.setText(element);
            }
        };

        WindowListBroadcastCommand windowListBroadcastCommand = new WindowListBroadcastCommand(project, device);
        windowListBroadcastCommand.setWindowCallback(callback);
        windowListBroadcastCommand.execCommand();
    }

    private void openWindowListCategory() {

        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(String element) {
                categoryTextField.setText(element);
            }
        };

        WindowListCategoryCommand windowListCategoryCommand = new WindowListCategoryCommand(project);
        windowListCategoryCommand.setWindowCallback(callback);
        windowListCategoryCommand.execCommand();
    }

    private void openWindowListComponent() {

        WindowCallback callback = new WindowCallback() {
            @Override
            public void handleReturn(String componentName) {
                componentTextField.setText(componentName);
            }
        };

        String action = actionTextField.getText();

        WindowListComponent windowListComponent = new WindowListComponent(project, device, action);
        windowListComponent.setCallback(callback);
        windowListComponent.execCommand();
    }

    private void sendIntent() {

        if (device != null && device.isOnline()) {
            ExtraTableModel extraModel = (ExtraTableModel) extraTable.getModel();
            IntentType intentType = (IntentType) typeIntentCombobox.getSelectedItem();

            IntentString intentString = new IntentString(intentType);
            intentString.setAction(actionTextField.getText());
            intentString.setCategory(categoryTextField.getText());
            intentString.setComponent(componentTextField.getText());

            intentString.setExtraComponent(extraModel);

            SendAdbCommand.SendIntentCallback sendIntentCallback = new SendAdbCommand.SendIntentCallback() {
                @Override
                public void handleError(Exception e) {
                    appendStringIntoMessageTextArea(e.getMessage());
                }

                @Override
                public void handleResult(boolean worked, String[] messageReturn) {

                    for (String line : messageReturn) {
                        appendStringIntoMessageTextArea(line);
                    }
                }

                @Override
                public void onFinishedProcess() {

                }
            };

            if (intentString.isActionConfigured()) {
                SendAdbCommand command = new SendAdbCommand(project, device, intentString.getFinalIntent());
                command.setCallback(sendIntentCallback);
                command.execCommand();
            } else {

                appendStringIntoMessageTextArea("Action should be configured");
            }

        } else {
            appendStringIntoMessageTextArea("Should select a online device");
        }
    }

    private void resetMessageTextArea() {
        logTextArea.setText("");
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

            comboboxModel.updateDeviceList(devices);
            deviceCombobox.updateUI();
        }
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        DialogWrapperAction sendActionButton = new DialogWrapperAction("Send") {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                sendIntent();
            }
        };

        DialogWrapperAction cancelActionButton = new DialogWrapperAction("Cancel") {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                onCancel();
            }
        };

        return new Action[] { };
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

    private void appendStringIntoMessageTextArea(String line) {
        logTextArea.append(String.format("%s\n", line));
    }
}
