package scripts.Requirements;

import org.tribot.api2007.types.RSItem;

/**
 * Taken from Final Caliburs Scripter applications
 */

public class ItemReq {

    private int playerAmt;

    private int id;
    private int amount;

   public ItemReq(int itemId, int amount){
        this.id = itemId;
        this.amount = amount;
    }


    public int getId() {
        return id;
    }


    public int getAmount() {
        return amount;
    }

    public void check(RSItem[] items) {
        for(RSItem i : items)
            if(i.getID() == id)
                playerAmt += i.getStack();
    }
}
