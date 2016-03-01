package br.com.braga.adb.ui;

import br.com.braga.adb.ui.window.menus.ActivityMenuItem;
import br.com.braga.adb.ui.window.menus.BroadcastMenuItem;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.util.List;

/**
 * Created by ericbraga on 25/02/16.
 */
public class PopupMenuFactory {

    private List<String> popUpStringItems;

    private final String RESOURCE_ICONS_FOLDER = "/icons";

    private Project project;

    public PopupMenuFactory(Project project) {
        this.project = project;
    }

    public JPopupMenu createJPopupMenu() {
        JPopupMenu popup = new JPopupMenu();
        popup.setSize(350, 10);

        popup.add(startAndroidComponentMenu());
        popup.add(configComponentMenu());
        popup.add(toggleApplicationComponentMenu());
        popup.add(clearDataComponentMenu());

        popup.add(contentProviderComponentMenu());
        popup.add(reverseAdbComponentMenu());
        popup.add(dumpsysComponentMenu());

        return popup;
    }

    private JMenuItem startAndroidComponentMenu() {

        JMenuItem item = new JMenu("Start");
        item.add(new ActivityMenuItem( project ));
        item.add(new BroadcastMenuItem( project ));
        item.add(new JMenuItem("Service", getResourceIcon("service.png")));

        return item;
    }

    private Icon getResourceIcon(String icon) {
        return IconLoader.getIcon(String.format("%s/%s", RESOURCE_ICONS_FOLDER, icon));
    }

    private JMenuItem configComponentMenu() {
        return new JMenuItem("Android Configurations");
    }

    private JMenuItem toggleApplicationComponentMenu() {
        return new JMenuItem("Enable / Disable Package", getResourceIcon("toggle.png"));
    }

    private JMenuItem clearDataComponentMenu() {
        return new JMenuItem("Clear Package Data");
    }

    private JMenuItem contentProviderComponentMenu() {
        JMenuItem item = new JMenu("Content Provider Actions");
        item.add(new JMenuItem("Query"));
        item.add(new JMenuItem("Insert"));
        item.add(new JMenuItem("Update"));
        item.add(new JMenuItem("Delete"));

        return item;
    }

    private JMenuItem reverseAdbComponentMenu() {
        JMenuItem item = new JMenu("Reverse");
        item.add(new JMenuItem("Reverse Phone Tcp"));
        item.add(new JMenuItem("List Reversed Phones"));
        item.add(new JMenuItem("Remove Reversed Phone"));
        item.add(new JMenuItem("Remove All"));
        return item;
    }

    private JMenuItem dumpsysComponentMenu() {
        return new JMenuItem("Dumpsys");
    }
}
