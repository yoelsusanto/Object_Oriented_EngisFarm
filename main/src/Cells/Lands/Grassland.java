//package src;
package src.Cells.Lands;

import src.Cells.Land;

public class Grassland extends Land {
    public Grassland(boolean occupied){
      super('-',6);
      this.occupied = occupied;
    }
}
