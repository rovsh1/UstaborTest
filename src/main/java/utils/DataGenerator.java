package utils;

import com.github.javafaker.Faker;
import entities.Master;
import entities.Category;
import entities.User;

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
        user.setFirstName(faker.name().firstName() + faker.number().digits(4));
        user.setLastName(faker.name().lastName() + faker.number().digits(4));
        user.setAboutMe(faker.name().fullName());

        return user;
    }

    public static Master getMaster(Category category) {
        var master = getMaster();
        master.setCategory(category);
        return master;
    }
}
