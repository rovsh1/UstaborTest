package entities;

import java.util.Random;

public class Project {

    private String name;
    private String category;
    private String description = "This is project description";
    private String systemId;

    public Project(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public Project(String category) {
        this.name = String.format("Project#%s", new Random().nextInt(9999999));
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
