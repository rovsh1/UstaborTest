package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import entities.User;

import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private Faker faker;
    private FakeValuesService fakeValuesService;

    public DataGenerator() {
        faker = new Faker();
        fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
    }

    public String getPassword() {
        return faker.internet().password();
    }

    public User getFullInfoUserValidEmail(String email) {
        String randomEmail = email.substring(0, email.indexOf('@'))
                + "+"
                + fakeValuesService.bothify("????####@gmail.com");

        User user = new User(
                randomEmail,
                faker.internet().password(),
                faker.phoneNumber().phoneNumber()
        );
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        return user;
    }

    public User getFullInfoUserRandomEmail() {
        User user = new User(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().phoneNumber()
        );
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        return user;
    }

    public int getRandomNumber() {
        return new Random().nextInt(9999999);
    }
}
