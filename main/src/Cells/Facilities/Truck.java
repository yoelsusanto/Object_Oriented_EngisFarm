package src.Cells.Facilities;

import org.apache.commons.lang3.mutable.MutableInt;

import src.Product;
import src.Cells.Facility;
import src.List;
import src.Product.*;


public class Truck extends Facility {
    private int lastUsed;
    boolean truckStatus;

    public Truck() {
        super('T',2);
        lastUsed=0;
    }

    public boolean checkStatus() {
        return truckStatus;
    }

    public void setStatus(boolean newStatus) {
        truckStatus=newStatus;
    }

    public void sellProduct(List<Product> bag, MutableInt gameMoney, int gameTime) {
        if ((gameTime-lastUsed)%3==0) {
            setStatus(true);
        }

        if (truckStatus) {
            if (bag.getNeff()>0) {
                int moneytemp = gameMoney.intValue();
                int idx = 0;
                while (idx < bag.getNeff()) {
                    moneytemp += bag.get(idx).getPrice();
                }
                gameMoney.setValue(moneytemp);
            } else {
                System.out.println("Tidak ada product yang bisa dijual!");
            }
        } else {
            System.out.println("Tidak bisa memakai truck sekarang!");
        }
    }
}
