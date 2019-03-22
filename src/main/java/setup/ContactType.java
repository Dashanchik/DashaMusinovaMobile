package setup;

public enum ContactType {
    HOME("//android.widget.CheckedTextView[1]"),
    WORK("//android.widget.CheckedTextView[2]"),
    MOBILE("//android.widget.CheckedTextView[3]"),
    OTHER("//android.widget.CheckedTextView[4]");

    private String value;

    ContactType(String value) {
        this.value = value;
    }

    public String getType() {
        return value;
    }

}
