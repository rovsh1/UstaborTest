package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import entities.Master;
import entities.Project;
import entities.User;

import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private Faker faker;
    private FakeValuesService fakeValuesService;
    private String password = "Password1!";

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
                password,
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
                password,
                faker.phoneNumber().phoneNumber()
        );
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        return user;
    }

    public Master getFullInfoMasterRandomEmail() {
        Master user = new Master(
                faker.internet().emailAddress(),
                password,
                faker.phoneNumber().phoneNumber()
        );
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        return user;
    }

    public Master getFullInfoMasterValidEmail(String email) {
        String randomEmail = email.substring(0, email.indexOf('@'))
                + "+"
                + fakeValuesService.bothify("????####@gmail.com");

        Master user = new Master(
                randomEmail,
                password,
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

    public Project getProject(Master master) {
        return new Project(
                String.format("Project#%d", this.getRandomNumber()),
                master.getCategory(),
                "This is project description"
        );
    }
}
