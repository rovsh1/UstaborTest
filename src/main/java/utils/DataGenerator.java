package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import entities.Master;
import entities.TestCategory;
import entities.User;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final String password = "Password1!";

    public static String getPassword() {
        return new Faker().internet().password();
    }

    public static User getCustomer(String email) {
        var faker = new Faker();

        var user = new User(
                email,
                password,
                getCountryCode() + getPhoneNumber());
        user.setFirstName(faker.name().firstName());
        user.setCity(faker.address().cityName());

        return user;
    }

    public static User getGuestCustomer() {
        var faker = new Faker();
        var user = new User();

        user.setFirstName(faker.name().firstName());
        user.setPhoneNumber(getCountryCode() + getPhoneNumber());
        user.setCity(faker.address().cityName());

        return user;
    }

    public static Master getMasterRandomEmail() {
        var faker = new Faker();
        var service = new FakeValuesService(new Locale("en-GB"), new RandomService());

        var user = new Master();
        user.setPassword(password);
        user.setPhoneNumber("70" + getPhoneNumber());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());

        String randomEmail = service.bothify(".????####@fakeDomain.com");
        user.setEmail(user.getFirstName() + "." + user.getLastName() + randomEmail);

        return user;
    }

    public static Master getMasterRandomEmail(TestCategory category) {
        var master = getMasterRandomEmail();
        master.setCategoryId(category.getSystemId());
        master.setCategoryName(category.getName());

        return master;
    }

    public static Master getMasterValidEmail(String email) {
        var faker = new Faker();

        var user = new Master();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber("70" + getPhoneNumber());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        return user;
    }

    private static String getPhoneNumber() {
        if (Config.isUstabor()) {
            var faker = new Faker();
            return faker.number().digits(9);
        }
        var exclude = Arrays.asList(3, 4);
        var sb = new StringBuilder();
        var rand = new Random();

        while (true) {
            var firstDigit = rand.nextInt(10);
            if (exclude.contains(firstDigit)) continue;

            sb.append(firstDigit);

            var lastDigits = rand.nextInt((9999999 - 1000000) + 1) + 1000000;
            sb.append(lastDigits);

            return sb.toString();
        }
    }

    private static String getCountryCode() {
        if (Config.isUstabor()) {
            return "998";
        }
        return "770";
    }
}
