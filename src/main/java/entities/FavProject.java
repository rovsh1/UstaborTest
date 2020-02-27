package entities;

public class FavProject {

    private String masterName;
    private String category;

    public FavProject(String masterName, String category) {
        this.masterName = masterName;
        this.category = category;
    }

    public String getMaster() {
        return masterName;
    }

    public String getCategory() {
        return category;
    }
}
