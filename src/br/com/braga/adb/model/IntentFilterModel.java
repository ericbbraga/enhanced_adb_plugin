package br.com.braga.adb.model;

public class IntentFilterModel {
    private String name;

    private String presentationName;

    IntentFilterModel(String name, String presentationName) {
        this.name = name;
        this.presentationName = presentationName;
    }

    public String getName() {
        return name;
    }

    public String getPresentationName() {
        return presentationName;
    }

    public String getValue() {
        return getName();
    }
}
