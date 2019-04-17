package src;

public class Cell extends Renderable{
    protected final int typeCell;
    protected boolean occupied;
    protected boolean hasGrass;

    public Cell() {
        typeCell=0;
    }

    public Cell(char renderChar, int typeCell, boolean isOccupied) {
        super(renderChar);
        this.typeCell=typeCell;
        occupied=isOccupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean newStat) {
        occupied=newStat;
    }

    public int getTypeCell() {
        return typeCell;
    }

    public boolean isHasGrass() {
        return hasGrass;
    }

    public void setHasGrass(boolean newStat) {
        //grassland
        if (getTypeCell()==6) {
            if (newStat) {
                renderChar='*';
            } else {
                renderChar='-';
            }
            //barn
        } else if (getTypeCell()==4) {
            if (newStat) {
                renderChar = '@';
            } else {
                renderChar = 'x';
            }
            //coop
        } else if (getTypeCell()==5) {
            if (newStat) {
                renderChar = '#';
            } else {
                renderChar = 'o';
            }
        }
        hasGrass = newStat;
    }
}
