package entities;

import java.util.Random;

public class Category {

    private String name = "Autotest" ;
    private String url = "autotest";
    private String systemId;
    private String promoId;
    private Project project;

    public Category() {
        project = new Project();
        var random = + new Random().nextInt(999999);
        name += random;
        url += random;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
