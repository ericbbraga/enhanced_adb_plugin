package br.com.braga.adb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 01/03/16.
 */
public class ActionBroadcastFactory {
    private List<ElementChoose> actions;

    public ActionBroadcastFactory() {
        actions = new ArrayList<>();

        actions.add(new IntentFilterModel("android.intent.action.TIME_TICK", "Intent.ACTION_TIME_TICK"));
        actions.add(new IntentFilterModel("android.intent.action.TIME_SET", "Intent.ACTION_TIME_CHANGED"));
        actions.add(new IntentFilterModel("android.intent.action.TIMEZONE_CHANGED", "Intent.ACTION_TIMEZONE_CHANGED"));
        actions.add(new IntentFilterModel("android.intent.action.BOOT_COMPLETED", "Intent.ACTION_BOOT_COMPLETED"));
        actions.add(new IntentFilterModel("android.intent.action.PACKAGE_ADDED", "Intent.ACTION_PACKAGE_ADDED"));
        actions.add(new IntentFilterModel("android.intent.action.PACKAGE_CHANGED", "Intent.ACTION_PACKAGE_CHANGED"));
        actions.add(new IntentFilterModel("android.intent.action.PACKAGE_REMOVED", "Intent.ACTION_PACKAGE_REMOVED"));
        actions.add(new IntentFilterModel("android.intent.action.PACKAGE_RESTARTED", "Intent.ACTION_PACKAGE_RESTARTED"));
        actions.add(new IntentFilterModel("android.intent.action.PACKAGE_DATA_CLEARED", "Intent.ACTION_PACKAGE_DATA_CLEARED"));
        actions.add(new IntentFilterModel("android.intent.action.UID_REMOVED", "Intent.ACTION_UID_REMOVED"));
        actions.add(new IntentFilterModel("android.intent.action.BATTERY_CHANGED", "Intent.ACTION_BATTERY_CHANGED"));
        actions.add(new IntentFilterModel("android.intent.action.POWER_CONNECTED", "Intent.ACTION_POWER_CONNECTED"));
        actions.add(new IntentFilterModel("android.intent.action.POWER_DISCONNECTED", "Intent.ACTION_POWER_DISCONNECTED"));
        actions.add(new IntentFilterModel("android.intent.action.SHUTDOWN", "Intent.ACTION_SHUTDOWN"));
    }

    public List<ElementChoose> getListAction() {
        ArrayList<ElementChoose> actionsCopy = new ArrayList<>();
        actionsCopy.addAll(actions);
        return actionsCopy;
    }
}
