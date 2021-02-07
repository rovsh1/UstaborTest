package entities;

import java.util.Random;

public class Project {

    private String name;
    private String description = "This is project description";
    private String systemId;
    private String promoId;

    public Project() {
        this.name = String.format("Project#%s", new Random().nextInt(9999999));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }
}
