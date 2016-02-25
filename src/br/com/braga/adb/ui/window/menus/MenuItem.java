package br.com.braga.adb.ui.window.menus;

import br.com.braga.adb.ui.listener.MenuActionListener;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Created by ericbraga on 25/02/16.
 */
public class MenuItem extends JMenuItem {

    private final String RESOURCE_ICONS_FOLDER = "/icons";

    private MenuActionListener action;

    public MenuItem(String title, String iconResource, MenuActionListener actionListener) {
        super(title);
        setIcon( getResourceIcon(iconResource) );
        addActionListener(actionListener);
    }

    private Icon getResourceIcon(String icon) {
        return IconLoader.getIcon(String.format("%s/%s", RESOURCE_ICONS_FOLDER, icon));
    }
}
