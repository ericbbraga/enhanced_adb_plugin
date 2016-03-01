package br.com.braga.adb.ui.window;

import br.com.braga.adb.model.ElementChoose;
import br.com.braga.adb.model.IntentType;
import br.com.braga.adb.ui.adapter.IntentTypeModel;
import br.com.braga.adb.ui.command.WindowStrategy;
import br.com.braga.adb.ui.command.WindowListActionBroadcastCommand;
import br.com.braga.adb.ui.command.WindowListActionCommand;
import br.com.braga.adb.ui.command.WindowListCategoryCommand;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class IntentWindowActionDialog extends DialogWrapper {
    private final Project project;
    private JPanel contentPane;
    private JButton sendButton;
    private JButton cancelButton;
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
    private ElementChoose actionChoose;
    private ElementChoose categoryChoose;


    public IntentWindowActionDialog(Project project) {
        super(project);
        this.project = project;
        init();
    }

    @Override
    protected void init() {
        super.init();

        setModal(true);
        setResizable(true);
        setTitle("Start Intent");
        contentPane.setSize(458, 650);

        getRootPane().setDefaultButton(sendButton);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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

            default:
                break;
        }
    }

    private void openWindowListAction() {
        WindowStrategy strategy = new WindowStrategy() {
            @Override
            public void handleReturn(ElementChoose element) {
                actionChoose = element;
                actionTextField.setText(element.getValue());
            }
        };

        new WindowListActionCommand(project, strategy).execCommand();
    }

    private void openWindowListCategory() {

        WindowStrategy strategy = new WindowStrategy() {
            @Override
            public void handleReturn(ElementChoose element) {
                categoryChoose = element;
                categoryTextField.setText(element.getValue());
            }
        };

        new WindowListCategoryCommand(project, strategy).execCommand();
    }

    private void openWindowListBroadcastAction() {

        WindowStrategy strategy = new WindowStrategy() {
            @Override
            public void handleReturn(ElementChoose element) {
                actionChoose = element;
                actionTextField.setText(element.getValue());
            }
        };

        new WindowListActionBroadcastCommand(project, strategy).execCommand();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }
}
