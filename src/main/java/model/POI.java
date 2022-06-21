package model;

public class POI {

    private String name;
    private String address;
    private String link;

    public POI(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }
}
