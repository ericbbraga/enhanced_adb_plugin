package br.com.braga.adb.model;

/**
 * Created by ericbraga on 01/03/16.
 */
public enum IntentType {
    ACTIVITY("Activity"),
    BROADCAST("Broadcast"),
    SERVICE("Service");

    IntentType(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
