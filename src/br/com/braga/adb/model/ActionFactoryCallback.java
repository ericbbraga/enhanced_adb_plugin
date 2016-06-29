package br.com.braga.adb.model;

import java.util.List;

/**
 * Created by ericbraga on 29/06/16.
 */
public interface ActionFactoryCallback {
    void handleError(Exception e);
    void onFinishedProcess(List<String> actions);

}
