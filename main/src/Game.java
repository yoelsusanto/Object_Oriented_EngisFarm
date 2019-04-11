package src;


import src.Farm_Animal.Bebek;

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
            y = (int) ((Math.random()*100%11);

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
            y = (int) ((Math.random()*100%11);

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
            y = (int) ((Math.random()*100%11);

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

    }


}
