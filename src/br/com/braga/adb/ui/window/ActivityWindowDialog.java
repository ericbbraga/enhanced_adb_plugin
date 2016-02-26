package br.com.braga.adb.ui.window;

import javax.swing.*;
import java.awt.event.*;

public class ActivityWindowDialog extends JDialog {
    private JPanel contentPane;
    private JButton sendButton;
    private JButton cancelButton;
    private JTable extraTable;
    private JList flagList;
    private JTextField textField1;
    private JButton button3;
    private JButton button4;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button5;
    private JPanel panelMain;

    public ActivityWindowDialog() {
        setContentPane(contentPane);
        setModal(true);
        setResizable(false);
        setSize(458, 650);

        setLocationRelativeTo(null);
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

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ActivityWindowDialog dialog = new ActivityWindowDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
