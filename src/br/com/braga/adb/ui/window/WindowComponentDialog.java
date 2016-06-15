package br.com.braga.adb.ui.window;

import br.com.braga.adb.ui.adapter.WindowComponentListModel;
import br.com.braga.adb.ui.command.WindowComponentCallback;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class WindowComponentDialog extends DialogWrapper {
    private JPanel contentPane;
    private JList componentList;
    private JTextField componentFilter;
    private JButton buttonOK;
    private JButton buttonCancel;

    private List<String> components;
    private List<String> filteredComponents;
    private WindowComponentCallback callback;
    private WindowComponentListModel model;

    public WindowComponentDialog(Project project, List<String> components, String title) {
        super( project );
        this.components = new ArrayList<>(components);
        filteredComponents = new ArrayList<>(components);
        setTitle(title);
        init();
    }

    public void setCallback(WindowComponentCallback callback) {
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
                filtercomponents();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtercomponents();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtercomponents();
            }
        });
    }

    private void filtercomponents() {

        String filterText = componentFilter.getText();

        if (filterText.length() == 0) {
            filteredComponents.addAll(components);

        } else {
            filteredComponents.clear();

            for (String component : components) {
                if (component.contains(filterText)) {
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
            if (callback != null) {
                callback.handleReturn((String) componentList.getSelectedValue());
            }

            dispose();
        }
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
