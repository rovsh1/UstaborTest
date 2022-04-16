package utils;

import entities.Master;
import entities.Category;
import entities.User;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.List;

public class Watcher extends TestWatcher {

    public List<User> users = new ArrayList<>();
    public User customer;
    public Category category;

    @Override
    protected void finished(Description description) {
        cleanUp();
    }

    private void cleanUp() {
        users.forEach((user) -> {
            if (user.getProfileId() != null) {

                switch (user.getClass().getSimpleName()) {
                    case "Master":
                        Admin.getInstance().deleteMaster(user.getProfileId());
                        break;
                    case "User":
                        Admin.getInstance().deleteCustomer(user.getProfileId());
                        break;
                }
            }
        });

        if (category != null && category.getSystemId() != null) {
            Admin.getInstance().deleteCategory(category.getSystemId());
        }
    }

    public Master getMaster() {
        return (Master) users.stream()
                .filter(user -> user.getClass().getSimpleName().equals("Master"))
                .findFirst()
                .orElseThrow();
    }

}
