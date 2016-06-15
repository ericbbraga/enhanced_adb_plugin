package br.com.braga.adb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 15/06/16.
 */
public class FlagsFactory {
    private List<FilterModel> flags;

    public FlagsFactory() {
        flags = new ArrayList<>();
        flags.add(new FilterModel("--grant-read-uri-permission", "FLAG_GRANT_READ_URI_PERMISSION"));
        flags.add(new FilterModel("--grant-write-uri-permission", "FLAG_GRANT_WRITE_URI_PERMISSION"));
        flags.add(new FilterModel("--exclude-stopped-packages", "FLAG_EXCLUDE_STOPPED_PACKAGES"));

        flags.add(new FilterModel("--include-stopped-packages", "FLAG_INCLUDE_STOPPED_PACKAGES"));
        flags.add(new FilterModel("--debug-log-resolution", "FLAG_DEBUG_LOG_RESOLUTION"));
        flags.add(new FilterModel("--activity-brought-to-front", "FLAG_ACTIVITY_BROUGHT_TO_FRONT"));
        flags.add(new FilterModel("--activity-clear-top", "FLAG_ACTIVITY_CLEAR_TOP"));

        flags.add(new FilterModel("--activity-clear-when-task-reset", "FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET"));
        flags.add(new FilterModel("--activity-exclude-from-recents", "FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS"));
        flags.add(new FilterModel("--activity-launched-from-history", "FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY"));
        flags.add(new FilterModel("--activity-multiple-task", "FLAG_ACTIVITY_MULTIPLE_TASK"));

        flags.add(new FilterModel("--activity-no-animation", "FLAG_ACTIVITY_NO_ANIMATION"));
        flags.add(new FilterModel("--activity-no-history", "FLAG_ACTIVITY_NO_HISTORY"));
        flags.add(new FilterModel("--activity-no-user-action", "FLAG_ACTIVITY_NO_USER_ACTION"));
        flags.add(new FilterModel("--activity-previous-is-top", "FLAG_ACTIVITY_PREVIOUS_IS_TOP"));

        flags.add(new FilterModel("--activity-reorder-to-front", "FLAG_ACTIVITY_REORDER_TO_FRONT"));
        flags.add(new FilterModel("--activity-reset-task-if-needed", "FLAG_ACTIVITY_RESET_TASK_IF_NEEDED"));
        flags.add(new FilterModel("--activity-single-top", "FLAG_ACTIVITY_SINGLE_TOP"));
        flags.add(new FilterModel("--activity-clear-task", "FLAG_ACTIVITY_CLEAR_TASK"));

        flags.add(new FilterModel("--activity-task-on-home", "FLAG_ACTIVITY_TASK_ON_HOME"));
        flags.add(new FilterModel("--receiver-registered-only", "FLAG_RECEIVER_REGISTERED_ONLY"));
        flags.add(new FilterModel("--receiver-replace-pending", "FLAG_RECEIVER_REPLACE_PENDING"));
    }

    public List<FilterModel> getList() {
        return new ArrayList<>(flags);
    }

}
