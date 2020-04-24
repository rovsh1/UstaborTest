package entities;

public class Master extends User {

    private String category;
    private String categoryId;
    private String projectUrl;
    private String profileUrl;
    private String profileId = null;
    private int countOfBadges = 0;

    public Master(String login, String password, String phoneNumber) {
        super(login, password, phoneNumber);
    }

    public Master() {
        super();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String toString() {
        return String.format("Login: '%s', SystemId: '%s'", getLogin(), getProfileId());
    }
}
