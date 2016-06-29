package br.com.braga.adb.model;

/**
 * Created by ericbraga on 29/06/16.
 */
public class ServiceActionFactory extends ActionFactory {

    private static final String DELIMITER_SERVICE_GROUP = "Service Resolver Table";

    @Override
    protected String getDelimiterGroupAction() {
        return DELIMITER_SERVICE_GROUP;
    }
}
