package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import entities.Master;
import entities.TestCategory;
import entities.User;

import java.util.Locale;

public class DataGenerator {
    private static final String password = "Password1!";

    public static String getPassword() {
        return new Faker().internet().password();
    }

    public static User getCustomer(String email) {
        var faker = new Faker();

        var user = new User(email, password, faker.phoneNumber().cellPhone());
        user.setFirstName(faker.name().firstName());
        user.setCity(faker.address().cityName());

        return user;
    }

    public static User getGuestCustomer() {
        var faker = new Faker();
        var user = new User();

        user.setFirstName(faker.name().firstName());
        user.setPhoneNumber(faker.number().digits(10));
        user.setCity(faker.address().cityName());

        return user;
    }

    public static Master getMasterRandomEmail() {
        var faker = new Faker();
        var service = new FakeValuesService(new Locale("en-GB"), new RandomService());

        var user = new Master();
        user.setPassword(password);
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());

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
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        return user;
    }
}
