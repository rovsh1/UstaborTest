package entities;

public class Master extends User {

    private String categoryName;
    private String categoryId;
    private String projectUrl;
    private String profileUrl;
    private String profileId = null;
    private Project project;

    private int countOfBadges = 0;

    public Master() {
        super();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public int getCountOfBadges() {
        return countOfBadges;
    }

    public void setCountOfBadges(int countOfBadges) {
        this.countOfBadges = countOfBadges;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String toString() {
        return String.format("Login: '%s', SystemId: '%s'", getEmail(), getProfileId());
    }
}
