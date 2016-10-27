package ly.generalassemb.drewmahrt.shoppinglistver2;

/**
 * Created by Scott Lindley on 10/25/2016.
 */

public class GroceryItem {
    private String mName, mDescription, mType, mPrice;

    public GroceryItem(String name, String description, String type, String price) {
        mName = name;
        mDescription = description;
        mType = type;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getType() {
        return mType;
    }


    public String getPrice() {
        return mPrice;
    }

}
