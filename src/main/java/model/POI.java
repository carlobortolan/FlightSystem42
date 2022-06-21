package model;

public class POI {

    private String name;
    private String address;
    private String link;

    public POI(String link) {
        this.link = link;
        this.name = this.findName();
        this.address = this.findAddress();
    }

    private String findAddress() {
        return null;
    }

    private String findName() {
        return null;
    }

    public String getName() {
        return name;
    }
}
