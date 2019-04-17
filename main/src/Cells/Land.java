package src.Cells;

import  src.Cell;

public class Land extends Cell {

    public Land(char renderChar, int type) {
        super(renderChar, type, false);
        setHasGrass(true);
    }
}

