package br.com.braga.adb.ui.window;

import br.com.braga.adb.model.Extra;
import br.com.braga.adb.ui.adapter.ExtraColumnModel;
import br.com.braga.adb.ui.command.WindowExtraCallback;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.mxgraph.swing.util.mxGraphActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExtraWindowDialog extends DialogWrapper {
    private final WindowExtraCallback callback;
    private JPanel contentPane;
    private JComboBox typeCombobox;
    private JTextField nameText;
    private JTextField valueText;

    public ExtraWindowDialog(Project project, WindowExtraCallback callback) {
        super( project );
        this.callback = callback;
        init();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[] {new SelectAction(), getCancelAction()};
    }

    @Override
    protected void init() {
        super.init();
        setModal(true);
        setTitle("Extra Keys");

        contentPane.setPreferredSize(new Dimension(300, 200));

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        typeCombobox.setModel(new ExtraColumnModel());
        typeCombobox.setSelectedIndex(0);
    }

    private void onOK() {
        if (callback != null) {
            Extra.ExtraType extraType = (Extra.ExtraType) typeCombobox.getModel().getSelectedItem();

            Extra extra = new Extra(extraType, nameText.getText(), valueText.getText());
            callback.handleReturn(extra);
        }

        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    private class SelectAction extends DialogWrapper.DialogWrapperAction {
        protected SelectAction() {
            super("Select");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            onOK();
        }
    }
}
