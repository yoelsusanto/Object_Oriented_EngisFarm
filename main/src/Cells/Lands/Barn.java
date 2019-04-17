package src.Cells.Lands;

import src.Cells.Land;

public class Barn extends Land{
    public Barn (boolean occupied){
        super('x',4);
        this.setOccupied(occupied);
    }
}
