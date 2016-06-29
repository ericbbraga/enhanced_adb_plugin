package br.com.braga.adb.ui.command;

import com.intellij.openapi.project.Project;

/**
 * Created by ericbraga on 29/02/16.
 */
public abstract class AbstractCommand implements Command {

    private Project project;

    public AbstractCommand(Project project) {
        this.project = project;
    }

    protected Project getProject() {
        return project;
    }

}
