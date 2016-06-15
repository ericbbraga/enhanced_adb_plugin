package br.com.braga.adb.ui.window;

import br.com.braga.adb.model.FilterModel;
import br.com.braga.adb.ui.adapter.WindowListModel;
import br.com.braga.adb.ui.command.WindowCallback;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class WindowActionsDialog extends DialogWrapper {
    private JPanel contentPane;
    private WindowListModel windowListModel;
    private List<FilterModel> elements;
    private JList list;
    private WindowCallback callback;
    private String title;

    private int selectionMode;

    public WindowActionsDialog(Project project, List<FilterModel> elements, String title) {
        super( project );
        this.elements = elements;
        windowListModel = new WindowListModel(elements);
        setTitle(title);

        setSingleMode();

        init();
    }

    public void setSingleMode() {
        selectionMode = ListSelectionModel.SINGLE_SELECTION;
    }

    public void setMultipleMode() {
        selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
    }


    @Override
    protected Action[] createActions() {
        return new Action[] { new SelectAction(), getCancelAction() };
    }

    @Override
    protected void init() {
        super.init();
        setModal(true);
        contentPane.setPreferredSize(new Dimension(350, 200));

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        list.setModel( windowListModel );
        list.setSelectionMode(selectionMode);
    }

    public void setCallback(WindowCallback callback) {
        this.callback = callback;
    }

    private class SelectAction extends DialogWrapper.DialogWrapperAction {
        protected SelectAction() {
            super("Select");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            if (callback != null) {
                FilterModel element = elements.get(list.getSelectedIndex());
                callback.handleReturn( element );
            }

            dispose();
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
