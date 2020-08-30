//import annotations.AddCategory;
//import annotations.AddMasters;
//import entities.Project;
//import entities.TestCategory;
//import org.junit.Before;
//
//import java.util.concurrent.TimeoutException;
//
//public class ProdTestBase extends TestBase {
//
//    private final boolean isAddCategory;
//    private final boolean isAddMasters;
//    private int mastersCount = 0;
//
//    public final TestCategory category = new TestCategory();
//
//    ProdTestBase() {
//        isAddCategory = this.getClass().isAnnotationPresent(AddCategory.class);
//        isAddMasters = this.getClass().isAnnotationPresent(AddMasters.class);
//        if (isAddMasters) {
//            mastersCount = this.getClass().getAnnotation(AddMasters.class).value();
//        }
//    }
//
//    @Before
//    public void setUp() throws TimeoutException {
//        super.setUp();
//        if (isAddCategory) {
//            watcher.category = category;
//            admin.addTestCategory(category);
//            admin.addTestCategoryRequestQuestions(category, "This is a test question", "100", "200");
//
//            if (isAddMasters) {
//                for (int i = 0; i < mastersCount; i++) {
//                    var master = data.getMasterRandomEmail(category);
//                    watcher.masters.add(master);
//
//                    user.atHomePage.openHomePage();
//                    user.atHomePage.registerAsMasterWithSpecifiedCategory(master);
//                    user.atMasterProfilePage.waitForPageIsVisible();
//                    master.setProfileId(user.atMasterProfilePage.getProfileId());
//
//                    var project = new Project(master.getCategoryName());
//                    user.atMasterProjectsPage.openProjectsTab();
//                    user.atMasterProjectsPage.addNewProjectInCategory(project, false, false);
//                    user.atHomePage.logsOut();
//                }
//            }
//        }
//    }
//}
