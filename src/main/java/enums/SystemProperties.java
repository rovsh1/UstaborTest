package enums;

public enum SystemProperties {

    SITE("site"),
    LANG("lang"),
    COUNTRY("country"),
    BROWSER("browser");

    private final String prop;

    SystemProperties(String prop) {
        this.prop = prop;
    }

    @Override
    public String toString() {
        return prop;
    }

}
