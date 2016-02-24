package br.com.braga.adb;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.android.sdk.AndroidSdkUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by ericbraga on 23/02/16.
 */
public class AdbDrawerViewer implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        AdbViewer viewer = new AdbViewer();

        final ContentManager contentManager = toolWindow.getContentManager();
        final Content content = contentManager.getFactory().createContent(viewer, null, false);
        contentManager.addContent(content);

        AndroidDebugBridge bridge = AndroidSdkUtils.getDebugBridge(project);
        if (bridge != null) {
            IDevice[] devices = bridge.getDevices();
            viewer.setDevices(Arrays.asList(devices));
        }
    }
}
