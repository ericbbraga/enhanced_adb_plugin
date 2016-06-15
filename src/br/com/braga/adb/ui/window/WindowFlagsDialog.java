package br.com.braga.adb.ui.window;

import br.com.braga.adb.model.FilterModel;
import br.com.braga.adb.ui.adapter.WindowFlagsModel;
import br.com.braga.adb.ui.command.WindowListFlagsCallback;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class WindowFlagsDialog extends DialogWrapper {
    private final List<FilterModel> flags;

    private JPanel contentPane;
    private JList flagList;
    private JTextArea flagDescription;

    private WindowListFlagsCallback callback;

    public WindowFlagsDialog(Project project, List<FilterModel> flags, String title) {
        super(project);
        this.flags = flags;
        setTitle(title);
        setModal(true);
        init();
    }

    public void setCallback(WindowListFlagsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void init() {
        super.init();
        setModal(true);

        contentPane.setPreferredSize(new Dimension(640, 320));

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        flagList.setModel(new WindowFlagsModel(this.flags));
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

                List<FilterModel> flagsSelected = new ArrayList<>();

                int[] flagsSelectedIndex = flagList.getSelectedIndices();
                for (int flagIndex : flagsSelectedIndex) {
                    flagsSelected.add(flags.get(flagIndex));
                }

                callback.handleReturn(flagsSelected);
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
