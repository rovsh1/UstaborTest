package utils;

import entities.Master;
import entities.TestCategory;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.List;

public class Watcher extends TestWatcher {

    public List<Master> masters = new ArrayList<>();
    public TestCategory category;

    @Override
    protected void finished(Description description) {
        cleanUp();
    }

    private void cleanUp() {
        for(Master master : masters) {
            if (master.getProfileId() != null) {
                new Admin().deleteMaster(master.getProfileId());
            }

        }
        if (Config.isProdEnv()) {
            if (category != null && category.getSystemId() != null) {
                new Admin().deleteCategory(category.getSystemId());
            }
        }
    }

}
