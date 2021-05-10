package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import entities.Master;
import entities.Category;
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
                faker.number().digits(9));
        user.setFirstName(faker.name().firstName());
        user.setCity(faker.address().cityName());

        return user;
    }

    public static User getCustomer() {
        var faker = new Faker();
        var user = new User();

        user.setPassword(password);
        user.setPhoneNumber(faker.number().digits(9));
        user.setFirstName(faker.name().firstName());
        user.setCity(faker.address().cityName());

        return user;
    }

    public static User getGuestCustomer() {
        var faker = new Faker();
        var user = new User();

        user.setFirstName(faker.name().firstName());
        user.setPhoneNumber(faker.number().digits(9));
        user.setCity(faker.address().cityName());

        return user;
    }

    public static Master getMaster() {
        var faker = new Faker();
        var user = new Master();

        user.setPassword(password);
        user.setPhoneNumber(faker.number().digits(9));
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());

        return user;
    }

    public static Master getMaster(Category category) {
        var master = getMaster();
        master.setCategory(category);
        return master;
    }
}
