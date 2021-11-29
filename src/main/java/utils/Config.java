package utils;

import enums.Browsers;
import enums.SystemProperties;
import net.serenitybdd.core.Serenity;

import java.util.Properties;

import static enums.SystemProperties.*;

public class Config {

    private static final String props = "config.properties";

    private static String env;
    private static String site;
    private static String lang;
    private static String country;
    private static String countryCode;
    private static String adminUrl;
    private static Users users;
    private static Browsers browser;

    public static Browsers getBrowser() {
        if (browser == null) {
            var parameter = getEnvironmentVariableValue(BROWSER, false);

            if (parameter == null) {
                browser = Browsers.chrome;
            } else {
                browser = Browsers.valueOf(parameter);
            }
        }
        return browser;
    }

    public static boolean isUstabor() {
        return getEnv().equals("ustabor");
    }

    public static boolean isFixListKg() {
        return getEnv().equals("fixinglist_kg");
    }

    public static boolean isFixinglist() {
        return getEnv().equals("fixinglist");
    }

    public static boolean isNewTest() {
        return getEnv().equals("new_test");
    }

    public static Users getUsers() {
        if (users == null) {
            users = new Users(getEnvironmentVariableValue(SITE, true));
        }

        return users;
    }

    public static String getAdminUrl() {
        var prefix = getEnvironmentVariableValue(SITE, true);

        if (adminUrl == null) {
            adminUrl = PropertyReader.getInstance().getProperty(prefix + ".site.admin", props);
        }

        return adminUrl;
    }

    public static String getFullUrl() {
        if (isUstabor() || isFixListKg()) return getBaseUrl();
        var langCountryCode = String.format("%s-%s/", getCountryCode(), getLang());
        if (isFixinglist() || isNewTest()) langCountryCode =  String.format("%s-%s/", getLang(), getCountryCode());
        return getBaseUrl() + langCountryCode;
    }

    private static String getBaseUrl() {
        if (site == null) {
            site = getPropertyFromEnvVariable(SITE, true);
        }

        if (isUstabor()) {
            country = getUzCountry();
            countryCode = getUzCountryCode();
        }

        return site + "/";
    }

    public static String getLang() {
        if (lang == null) {
            lang = getPropertyFromEnvVariable(LANG, true);
        }
        return lang;
    }

    public static String getCountry() {
        if (country == null) {
            country = XmlParser.getTextByKey(getCountryCode());
        }
        return country;
    }

    public static String getCountryCode() {
        if (countryCode == null) {
            if (isUstabor()) {
                countryCode = "uz";
            } else if (isFixListKg()) {
                countryCode = "kg";
            } else {
                countryCode = getEnvironmentVariableValue(COUNTRY, false);
            }
        }
        return countryCode;
    }

    public static String getEnv() {
        if (env == null) {
            env = getEnvironmentVariableValue(SITE, true);
        }
        return env;
    }

    private static String getPropertyFromEnvVariable(SystemProperties variable, boolean throwIfNull) {
        String varValue = getEnvironmentVariableValue(variable, throwIfNull);

        return PropertyReader.getInstance().getProperty(variable.toString() + "." + varValue, props);
    }

    private static String getEnvironmentVariableValue(SystemProperties variable, boolean errorIfNull) {
        String varValue = System.getProperty(variable.toString(), null);

        if (varValue == null) {
            if (errorIfNull) {
                Serenity.throwExceptionsImmediately();
                throw new NullPointerException(
                        String.format("You need to define '%s' environment variable before test run.", variable));
            }
        }
        return varValue;
    }

    private static String getUzCountry() {
        return PropertyReader.getInstance().getProperty("country.uz", props);
    }

    private static String getUzCountryCode() {
        return PropertyReader.getInstance().getProperty("lang.uz", props);
    }

    public static boolean isMobileTag() {
        Properties properties = System.getProperties();
        return properties.getProperty("tags") != null && properties.getProperty("tags").contains("mobile");
    }

    public static boolean isChrome() {
        return getBrowser().equals(Browsers.chrome);
    }

    public static String getChromeDriverPath() {
        var os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {

            return "driver/chromedriver.exe";

        } else if (os.contains("mac")) {

            return "driver/chromedriver";

        } else if (os.contains("nux")) {

            return "/var/lib/jenkins/workspace/chromedriver_linux";

        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
