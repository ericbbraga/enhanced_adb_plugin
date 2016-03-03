package br.com.braga.adb.ui.window;

import br.com.braga.adb.model.ElementChoose;
import br.com.braga.adb.ui.adapter.WindowListModel;
import br.com.braga.adb.ui.command.WindowStrategy;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class WindowListExtraDialog extends DialogWrapper {
    private JPanel contentPane;
    private WindowListModel windowListModel;
    private List<ElementChoose> elements;
    private JList list;
    private WindowStrategy callback;
    private String title;

    public WindowListExtraDialog(Project project, List<ElementChoose> elements, String title) {
        super( project );
        this.elements = elements;
        windowListModel = new WindowListModel(elements);
        setTitle(title);

        init();
    }

    @Override
    protected Action[] createActions() {
        return new Action[] { new OkAction(), getCancelAction() };
    }

    @Override
    protected void init() {
        super.init();
        setModal(true);

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        list.setModel( windowListModel );
    }

    public void setCallback(WindowStrategy callback) {
        this.callback = callback;
    }

    private class OkAction extends  DialogWrapper.DialogWrapperAction {
        protected OkAction() {
            super("Select");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            if (callback != null) {
                ElementChoose element = elements.get(list.getSelectedIndex());
                callback.handleReturn( element );
                dispose();
            }
        }
    }

    private void onCancel() {
        dispose();
    }

    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }
}
