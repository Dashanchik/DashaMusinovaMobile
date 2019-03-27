package setup.enums;

public enum AppType {
    WEB ("web"),
    NATIVE("native");

    public String value;

    AppType(String value) {
        this.value  = value;
    }

    public String getType() {
        return value;
    }
}
