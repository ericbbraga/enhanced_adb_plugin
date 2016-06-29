package br.com.braga.adb.ui.window;

import br.com.braga.adb.ui.adapter.WindowComponentListModel;
import br.com.braga.adb.ui.command.WindowCallback;
import br.com.braga.adb.ui.listener.DoubleClickMouseListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class WindowComponent extends DialogWrapper {

    private JPanel contentPane;
    private JList componentList;
    private JTextField componentFilter;
    private JButton buttonOK;
    private JButton buttonCancel;

    private List<String> components;
    private List<String> filteredComponents;
    private WindowCallback callback;
    private WindowComponentListModel model;

    public WindowComponent(Project project, List<String> components, String title) {
        super( project );
        this.components = new ArrayList<>(components);
        filteredComponents = new ArrayList<>(components);
        setTitle(title);
        init();
    }

    public void setCallback(WindowCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void init() {
        super.init();
        setModal(true);

        contentPane.setPreferredSize(new Dimension(640, 480));

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        model = new WindowComponentListModel(filteredComponents);
        componentList.setModel(model);

        componentFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }
        });

        componentList.addMouseListener(new DoubleClickMouseListener() {
            @Override
            public void triggerEvent() {
                selectElement();
            }
        });
    }

    private void filter() {

        String filterText = componentFilter.getText();

        if (filterText.length() == 0) {
            filteredComponents.addAll(components);

        } else {
            filteredComponents.clear();

            for (String component : components) {
                if (component.toLowerCase().contains(filterText.toLowerCase())) {
                    filteredComponents.add(component);
                }
            }
        }

        model.update(filteredComponents);
        componentList.updateUI();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[] {new SelectAction(), getCancelAction()};
    }

    private class SelectAction extends DialogWrapper.DialogWrapperAction {
        protected SelectAction() {
            super("Select");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            selectElement();
        }
    }

    private void selectElement() {
        if (callback != null) {
            callback.handleReturn((String) componentList.getSelectedValue());
        }

        dispose();
    }

    private void onCancel() {
        dispose();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }
}
