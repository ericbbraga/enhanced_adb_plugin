package br.com.braga.adb.actions;

import br.com.braga.adb.ui.command.IntentWidowCommand;
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
        new IntentWidowCommand(project).execCommand();
    }
}
