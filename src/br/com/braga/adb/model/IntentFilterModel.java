package br.com.braga.adb.model;

public class IntentFilterModel implements ElementChoose {
    private String name;

    private String presentationName;

    IntentFilterModel(String name, String presentationName) {
        this.name = name;
        this.presentationName = presentationName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPresentationName() {
        return presentationName;
    }

    @Override
    public String getValue() {
        return getName();
    }
}
