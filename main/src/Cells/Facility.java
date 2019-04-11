package src.Cells;

import src.Cell;

public class Facility extends Cell {

//    public Facility() {
//        super();
//    }

    public Facility(char renderChar, int type) {
        super(renderChar, type, true);
        setHasGrass(false);
    }
}
