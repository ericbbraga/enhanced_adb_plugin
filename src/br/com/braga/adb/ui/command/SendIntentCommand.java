package br.com.braga.adb.ui.command;

import com.android.ddmlib.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.android.sdk.AndroidSdkUtils;

import java.io.IOException;

/**
 * Created by ericbraga on 13/06/16.
 */
public class SendIntentCommand extends AbstractCommand {
    private IDevice device;

    private String strCommand;

    private SendIntentCallback callback;

    public SendIntentCommand(Project project, IDevice device, String command) {
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
        }
    }

    private class IntentComandReceiver extends MultiLineReceiver {

        @Override
        public void processNewLines(String[] strings) {
            StringBuilder sb = new StringBuilder();
            boolean worked = true;

            for (String s : strings) {
                if (s.matches("Error")) {
                    worked = false;
                }

                sb.append(s);
                System.out.println(s);
            }

            if (callback != null) {
                callback.handleResult(worked, sb.toString());
            }
        }

        @Override
        public boolean isCancelled() {
            return false;
        }
    }

    public interface SendIntentCallback {
        void handleError(Exception e);

        void handleResult(boolean worked, String messageReturn);
    }
}
