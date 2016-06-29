package br.com.braga.adb.model;

/**
 * Created by ericbraga on 29/06/16.
 */
public class BroadcastActionFactory extends ActionFactory {

    private static final String DELIMITER_RECEIVER_GROUP = "Receiver Resolver Table";

    @Override
    protected String getDelimiterGroupAction() {
        return DELIMITER_RECEIVER_GROUP;
    }
}
