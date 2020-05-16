package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import entities.Master;
import entities.Project;
import entities.TestCategory;
import entities.User;

import java.util.Locale;

public class DataGenerator {

    private final Faker faker;
    private final FakeValuesService fakeValuesService;
    private final String password = "Password1!";

    public DataGenerator() {
        faker = new Faker();
        fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
    }

    public String getPassword() {
        return faker.internet().password();
    }

    public User getCustomer(String email) {
        return new User(email, password, faker.phoneNumber().phoneNumber());
    }

    public Master getMasterRandomEmail() {
        var user = new Master();
        user.setPassword(password);
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setAboutMe(faker.name().fullName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());

        String randomEmail = fakeValuesService.bothify(".????####@fakeDomain.com");
        user.setEmail(user.getFirstName() + "." + user.getLastName() + randomEmail);

        return user;
    }

    public Master getMasterRandomEmail(TestCategory category) {
        var master = getMasterRandomEmail();
        master.setCategoryId(category.getSystemId());
        master.setCategoryName(category.getName());

        return master;
    }

    public Master getMasterValidEmail(String email) {
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
