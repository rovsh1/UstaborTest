package utils;

import entities.Master;
import entities.TestCategory;
import entities.User;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.List;

public class Watcher extends TestWatcher {

    public List<User> users = new ArrayList<>();
    public User customer;
    public TestCategory category;

    @Override
    protected void finished(Description description) {
        cleanUp();
    }

    private void cleanUp() {
        users.forEach((user) -> {
            if (user.getProfileId() != null) {

                switch (user.getClass().getSimpleName()) {
                    case "Master":
                        new Admin().deleteMaster(user.getProfileId());
                        break;
                    case "User":
                        new Admin().deleteCustomer(user.getProfileId());
                        break;
                }
            }
        });

        if (category != null && category.getSystemId() != null) {
            new Admin().deleteCategory(category.getSystemId());
        }
    }

}
