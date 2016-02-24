package br.com.braga.adb;

import com.android.ddmlib.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import org.jetbrains.android.sdk.AndroidSdkUtils;

import java.io.File;
import java.io.IOException;

public class AdbDevicesAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        final Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        AndroidDebugBridge bridge = AndroidSdkUtils.getDebugBridge(project);
        IDevice[] devices = bridge.getDevices();

        File adb = AndroidSdkUtils.getAdb(project);
        String path = adb.getAbsolutePath();

        if (devices.length > 0) {
            try {
                devices[0].executeShellCommand("getprop", new AdbDevicesReceiver());

            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (AdbCommandRejectedException e) {
                e.printStackTrace();
            } catch (ShellCommandUnresponsiveException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class AdbDevicesReceiver extends MultiLineReceiver {

        @Override
        public void processNewLines(String[] strings) {
            for (String s : strings) {
                System.out.println(s);
            }
        }

        @Override
        public boolean isCancelled() {
            return false;
        }
    }
}
