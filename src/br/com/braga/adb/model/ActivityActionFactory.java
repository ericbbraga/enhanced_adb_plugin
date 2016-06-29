package br.com.braga.adb.model;

/**
 * Created by ericbraga on 29/06/16.
 */
public class ActivityActionFactory extends ActionFactory {

    private static final String DELIMITER_ACTIVITY_GROUP = "Activity Resolver Table";

    @Override
    protected String getDelimiterGroupAction() {
        return DELIMITER_ACTIVITY_GROUP;
    }
}
