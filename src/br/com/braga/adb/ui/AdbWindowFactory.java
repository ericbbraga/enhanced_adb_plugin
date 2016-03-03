package br.com.braga.adb.ui;

import br.com.braga.adb.ui.window.AdbWindowPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

/**
 * Created by ericbraga on 23/02/16.
 */
public class AdbWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        AdbWindowPanel viewer = new AdbWindowPanel(project);

        final ContentManager contentManager = toolWindow.getContentManager();
        final Content content = contentManager.getFactory().createContent(viewer, null, false);
        contentManager.addContent(content);

        viewer.updateDevices();
    }
}
