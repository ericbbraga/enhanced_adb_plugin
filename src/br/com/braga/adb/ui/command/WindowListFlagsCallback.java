package br.com.braga.adb.ui.command;

import br.com.braga.adb.model.FilterModel;

import java.util.List;

/**
 * Created by ericbraga on 15/06/16.
 */
public interface WindowListFlagsCallback {
    void handleReturn(List<FilterModel> flags);
}
