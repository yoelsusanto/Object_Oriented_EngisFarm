package src;

import src.Farm_Animal.*;

public class Game {

    public final int mixer =1;
    public final int truck =2;
    public final int well =3;
    public final int barn =4;
    public final int coop =5;
    public final int grassland =6;

    private Cell map[][];
    private int gameTime;

    private Player P;

    private List<FarmAnimal> listOfAnimal;
    private List<Product> listOfProduct;

    public Game() {

    }

    public void initializeGame() {
        readMap("map.txt");
        gameTime=0;
        placePlayer();
        placeAnimal();
        P.setMoney(0);
        P.setWater(100);
    }

    public void forwardTime() {
        gameTime++;
        if (listOfAnimal.getNeff()==0) {
            System.out.println("End Game");
            System.exit(0);
        } else {
            liveAnimal();
            clearDeadAnimal();
        }
    }

    public void readMap(String namaFile) {

    }

    public void placeAnimal() {
        boolean berhasil=false, occupied;
        int x, y, typeCell;

        berhasil=false;
        //place angsa
        while (!berhasil) {

            x = (int) ((Math.random()*100%11);
            y = (int) ((Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            // cout << typeCell << endl;
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==coop)&&(!occupied)) {
                // cout << x << " " << y << "angsa" << endl;
                listOfAnimal.add(new Angsa(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place ayam
        while (!berhasil) {

            x = (int) ((Math.random()*100%11);
            y = (int) ((Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            // cout << typeCell << endl;
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==coop)&&(!occupied)) {
                // cout << x << " " << y << "angsa" << endl;
                listOfAnimal.add(new Ayam(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place bebek
        while (!berhasil) {

            x = (int) ((Math.random()*100%11);
            y = (int) ((Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            // cout << typeCell << endl;
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==coop)&&(!occupied)) {
                // cout << x << " " << y << "angsa" << endl;
                listOfAnimal.add(new Bebek(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place kambing
        while (!berhasil) {

            x = (int) ((Math.random()*100%11);
            y = (int) ((Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            // cout << typeCell << endl;
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==grassland)&&(!occupied)) {
                // cout << x << " " << y << "angsa" << endl;
                listOfAnimal.add(new Kambing(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place kuda
        while (!berhasil) {

            x = (int) ((Math.random()*100%11);
            y = (int) ((Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            // cout << typeCell << endl;
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==grassland)&&(!occupied)) {
                // cout << x << " " << y << "angsa" << endl;
                listOfAnimal.add(new Kuda(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place sapi
        while (!berhasil) {

            x = (int) ((Math.random()*100%11);
            y = (int) ((Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            // cout << typeCell << endl;
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==grassland)&&(!occupied)) {
                // cout << x << " " << y << "angsa" << endl;
                listOfAnimal.add(new Sapi(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

    }

    public void placePlayer() {
        boolean berhasil=false, occupied;
        int x, y, typeCell;

        //place manusia
        while (!berhasil) {
            x = (int) ((Math.random()*100%11);
            y = (int) ((Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            // cout << typeCell << endl;
            occupied = map[y][x].isOccupied();
            //barn or coop
            if (!occupied) {
                // cout << x << " " << y << "angsa" << endl;
                P = new Player(x,y);
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }
        // cout << "Placed player to: " << x << " " << y << endl;
    }

    public void movePlayer(int direction) {
        P.Move(direction, map);
    }

    public void liveAnimal() {
        for (int i=0; i<listOfAnimal.getNeff(); i++) {
            listOfAnimal.get(i).live(map);
        }
        // cout << "Menuakan animal succeed!" << endl;
    }

    public void clearDeadAnimal() {
        int i=0;
        while (i < listOfAnimal.getNeff()) {
            // cout << i+1 <<listOfAnimal.get(i)->getDead() << endl;
            if (listOfAnimal.get(i).getDead()) {
                int x = listOfAnimal.get(i).getX();
                int y = listOfAnimal.get(i).getY();
                map[y][x].setOccupied(false);
                listOfAnimal.removeIdx(i);
            } else {
                i++;
            }
        }
    }

    public void playerTalk() {
        P.Talk(listOfAnimal);
    }

    public void playerGrow() {
        P.Grow(map);
    }

    public void playerKill() {
        P.Kill(&listOfAnimal, map);
    }

    public void playerInteract() {
        P.Interact(listOfAnimal, map, gameTime);
    }

    public Player getPlayer() {
        return P;
    }

}
