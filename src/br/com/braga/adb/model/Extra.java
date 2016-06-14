package br.com.braga.adb.model;

/**
 * Created by ericbraga on 13/06/16.
 */
public class Extra {

    public enum ExtraType {
        INTEGER("Integer", "--ei"),
        BOOLEAN("Boolean", "--ez"),
        FLOAT("Float", "--ef"),
        URI("Uri", "--eu"),
        LONG("Long", "--el"),
        STRING("String", "-es");

        private final String strType;

        private final String extraParameter;

        ExtraType(String type, String extraParameter) {
            this.strType = type;
            this.extraParameter = extraParameter;
        }

        public String getStrType() {
            return strType;
        }

        public String getExtraParameter() {
            return extraParameter;
        }
    }

    private ExtraType type;

    private String key;

    private String value;

    public Extra(ExtraType type, String key, String value) {
        this.type = type;
        this.key  = key;
        this.value = value;
    }

    public ExtraType getType() { return type; }

    public String getValue() { return value; }

    public String getKey() { return key; }

}
