package setup.enums;

public enum AppType {
    WEB("web"),
    NATIVE("native");

    private String value;

    AppType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }

}
