package br.com.braga.adb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 01/03/16.
 */
public class ActionBroadcastFactory {
    private List<FilterModel> actions;

    public ActionBroadcastFactory() {
        actions = new ArrayList<>();

        actions.add(new FilterModel("android.intent.action.TIME_TICK", "Intent.ACTION_TIME_TICK"));
        actions.add(new FilterModel("android.intent.action.TIME_SET", "Intent.ACTION_TIME_CHANGED"));
        actions.add(new FilterModel("android.intent.action.TIMEZONE_CHANGED", "Intent.ACTION_TIMEZONE_CHANGED"));
        actions.add(new FilterModel("android.intent.action.BOOT_COMPLETED", "Intent.ACTION_BOOT_COMPLETED"));
        actions.add(new FilterModel("android.intent.action.PACKAGE_ADDED", "Intent.ACTION_PACKAGE_ADDED"));
        actions.add(new FilterModel("android.intent.action.PACKAGE_CHANGED", "Intent.ACTION_PACKAGE_CHANGED"));
        actions.add(new FilterModel("android.intent.action.PACKAGE_REMOVED", "Intent.ACTION_PACKAGE_REMOVED"));
        actions.add(new FilterModel("android.intent.action.PACKAGE_RESTARTED", "Intent.ACTION_PACKAGE_RESTARTED"));
        actions.add(new FilterModel("android.intent.action.PACKAGE_DATA_CLEARED", "Intent.ACTION_PACKAGE_DATA_CLEARED"));
        actions.add(new FilterModel("android.intent.action.UID_REMOVED", "Intent.ACTION_UID_REMOVED"));
        actions.add(new FilterModel("android.intent.action.BATTERY_CHANGED", "Intent.ACTION_BATTERY_CHANGED"));
        actions.add(new FilterModel("android.intent.action.POWER_CONNECTED", "Intent.ACTION_POWER_CONNECTED"));
        actions.add(new FilterModel("android.intent.action.POWER_DISCONNECTED", "Intent.ACTION_POWER_DISCONNECTED"));
        actions.add(new FilterModel("android.intent.action.SHUTDOWN", "Intent.ACTION_SHUTDOWN"));
    }

    public List<FilterModel> getListAction() {
        return new ArrayList<>(actions);
    }
}
