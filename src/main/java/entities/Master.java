package entities;

public class Master extends User {

    private String rating;
    private String feedback;
    private String profileUrl;
    private Category category;
    private int countOfBadges = 0;

    public Master() {
        super();
        category = new Category();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getCountOfBadges() {
        return countOfBadges;
    }

    public void setCountOfBadges(int countOfBadges) {
        this.countOfBadges = countOfBadges;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return String.format("Login: '%s', SystemId: '%s'", getEmail(), getProfileId());
    }


}
