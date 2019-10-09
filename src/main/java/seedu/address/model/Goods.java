package seedu.address.model;

/**
 * Deals with the name of the product to be delivered.
 */

public class Goods {

    private String name;

    public Goods(String name) {
        this.name = name;
    }

    /**
     * Edits the Name of the goods.
     *
     * @param newName The new name to be changed into
     */

    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return name;
    }
}
