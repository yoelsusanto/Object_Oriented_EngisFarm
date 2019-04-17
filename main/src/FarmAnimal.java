package src;

import src.Products.*;
import src.Products.SideProducts.*;
import src.Products.FarmProducts.*;

import java.lang.*;

public abstract class FarmAnimal extends Renderable {

    protected static int n_FarmAnimal;
    protected int typeAnimal;
    protected int timeLeft;
    protected int hungry;
    protected boolean hasProduct;
    protected boolean dead = false;
    protected int x;
    protected int y;
    protected String voice;
    protected boolean baruMove = false;

    public FarmAnimal(int _x, int _y, String _voice, int _typeAnimal){
        super();
        this.x = _x;
        this.y = _y;
        this.voice = _voice;
        this.typeAnimal = _typeAnimal;
    }
    public abstract void sound();

    public int getTypeAnimal(){
        return typeAnimal;
    }

    public final boolean getHasProduct(){
        return hasProduct;
    }

    public final int getX(){
        return x;
    }

    public final int getY(){
        return y;
    }

    public final String getVoice(){
        return voice;
    }

    public boolean getDead(){
        return dead;
    }

    public void setHasProduct(boolean _hasProduct){
        this.hasProduct = _hasProduct;
    }

    public void setHungry(int _hungry){
        this.hungry = _hungry;
    }

    public void setX(int _x){
        this.x = _x;
    }

    public void setY(int _y){
        this.y = _y;
    }

    public boolean isPointValid(int _y, int _x){
        return (_y >= 0 && _y < 10 && _x >= 0 && _x < 11);
    }

    public boolean isAreaValid(int tipeAnimal, int tipeArea){
        if (tipeAnimal == 1 || tipeAnimal == 2 || tipeAnimal == 3) {
            return (tipeArea == 4 || tipeArea == 5);
        }
        else {//(tipeAnimal == 4 || tipeAnimal == 5 || tipeAnimal == 6) {
            return (tipeArea == 4 || tipeArea == 6);
        }
    }

    public void live(Cell map[][]){
        if (timeLeft > 5) {
            timeLeft--;
            move(map);
        }
        else if (timeLeft > 0) {
            eat(map);
            move(map);
            timeLeft--;
        }
        else {
            dead = true;
            // kill animal
        }
    }
    public void eat(Cell map[][]){
        if (map[y][x].isHasGrass()) {
            map[y][x].setHasGrass(false);
            timeLeft = hungry+5+1;
            hasProduct = true;
        }
    }
    public void move(Cell map[][]){
        if (baruMove) {
            baruMove=false;
        } else {
            boolean success = false;
            int val = (int)(Math.random() * 4 + 1);
            int tries = 1;

            while (!success && tries <= 4) {
                switch (val) {
                    case 1:     // Up
                        if (isPointValid(y-1,x)){
                            if (!map[y-1][x].isOccupied() && isAreaValid(getTypeAnimal(),map[y-1][x].getTypeCell())) {
                                map[y][x].setOccupied(false);
                                y--;
                                map[y][x].setOccupied(true);
                                success = true;
                            }
                    }
                    break;
                    case 2:     // Down
                        if (isPointValid(y+1,x)){
                            if (!map[y+1][x].isOccupied() && isAreaValid(getTypeAnimal(),map[y+1][x].getTypeCell())) {
                                map[y][x].setOccupied(false);
                                y++;
                                map[y][x].setOccupied(true);
                                success = true;
                            }
                    }
                    break;
                    case 3:     // Left
                        if (isPointValid(y,x-1)){
                            if (!map[y][x-1].isOccupied() && isAreaValid(getTypeAnimal(),map[y][x-1].getTypeCell())) {
                                map[y][x].setOccupied(false);
                                x--;
                                map[y][x].setOccupied(true);
                                success = true;
                            }
                    }
                    break;
                    case 4:     // Right
                        if (isPointValid(y,x+1)){
                            if (!map[y][x+1].isOccupied() && isAreaValid(getTypeAnimal(),map[y][x+1].getTypeCell())) {
                                map[y][x].setOccupied(false);
                                x++;
                                map[y][x].setOccupied(true);
                                success = true;
                            }
                    }
                    break;
                }
                val++;
                if (val > 4) {
                    val = 1;
                }
                tries++;
                baruMove=true;
            }
        }
    }
    public abstract Product extract();
}