package ro.cs.upb.osp.lab2;

/**
 * Created by razvan on 07.07.2015.
 */
public class MyMenuItem {
    private float price;
    private String item;
    private String description;

    public MyMenuItem(String item, float price, String description) {
        this.item = item;
        this.price = price;
        this.description = description;
    }

    public String getPrice() {
        return Float.toString(price) + "$";
    }

    public String getName() {
        return item;
    }

    public String getDesc () {
        return description;
    }

    @Override
    public String toString() {
        return getName();
    }
}