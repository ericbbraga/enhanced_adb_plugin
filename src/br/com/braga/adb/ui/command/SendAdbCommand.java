package br.com.braga.adb.ui.command;

import com.android.ddmlib.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.android.sdk.AndroidSdkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 13/06/16.
 */
public class SendAdbCommand extends AbstractCommand {
    private IDevice device;

    private String strCommand;

    private SendIntentCallback callback;

    public SendAdbCommand(Project project, IDevice device, String command) {
        super(project);
        this.device = device;
        strCommand = command;
    }

    public void setCallback(SendIntentCallback callback) {
        this.callback = callback;
    }

    @Override
    public void execCommand() {
        final Project project = getProject();
        AndroidDebugBridge bridge = AndroidSdkUtils.getDebugBridge(project);

        if (bridge != null) {
            try {
                device.executeShellCommand(strCommand, new IntentComandReceiver());
            } catch (Exception e) {
                if (callback != null) {
                    callback.handleError(e);
                }
            }

            if (callback != null) {
                callback.onFinishedProcess();
            }
        }
    }

    private class IntentComandReceiver extends MultiLineReceiver {

        @Override
        public void processNewLines(String[] strings) {
            boolean worked = true;

            for (String s : strings) {
                if (s.matches("Error")) {
                    worked = false;
                    break;
                }
            }

            if (callback != null) {
                callback.handleResult(worked, strings);
            }
        }

        @Override
        public boolean isCancelled() {
            return false;
        }
    }

    public interface SendIntentCallback {
        void handleError(Exception e);

        void handleResult(boolean worked, String[] messageReturn);

        void onFinishedProcess();
    }
}
