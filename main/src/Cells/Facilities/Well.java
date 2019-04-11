package src.Cells.Facilities;

import org.apache.commons.lang3.mutable.MutableInt;
import src.Cells.Facility;



public class Well extends Facility {
    final int MAX_WATER=100;

    public Well() {
        super('W',3);
    }

    public void getWater(MutableInt waterBag) {
        if (waterBag.intValue()==MAX_WATER) {
            System.out.println("Ga ush tamak, air gratis kok");
        } else {
            waterBag.setValue(MAX_WATER);
        }
    }
}