package seedu.address.model;

public class Goods {

    private String name;

    public Goods(String name) {
        this.name = name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String toString() {
        return name;
    }
}
