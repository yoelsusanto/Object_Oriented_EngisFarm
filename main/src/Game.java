package src;

import src.Cells.Facilities.Mixer;
import src.Cells.Facilities.Truck;
import src.Cells.Facilities.Well;
import src.Cells.Lands.Barn;
import src.Cells.Lands.Coop;
import src.Cells.Lands.Grassland;
import src.Farm_Animal.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public final int mixer =1;
    public final int truck =2;
    public final int well =3;
    public final int barn =4;
    public final int coop =5;
    public final int grassland =6;

    private Cell[][] map;
    private int gameTime;

    private Player P;

    private List<FarmAnimal> listOfAnimal;
    private List<Product> listOfProduct;

    public Game() {
        listOfAnimal = new List<FarmAnimal>();
        listOfProduct = new List<Product>();
        map = new Cell[10][11];
        initializeGame();
    }

    public void initializeGame() {
        readMap("map.txt");
        gameTime=0;
        placePlayer();
        placeAnimal();
        P.setMoney(0);
        P.setWater(100);
    }

    void renderUI() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
        char [][] dummyMap = new char [10][11];
        for (int i=0; i<10; i++) {
            Arrays.fill(dummyMap[i],'\0');
        }
        int x,y;

        for (int i=0; i<listOfAnimal.getNeff(); i++) {
            x = listOfAnimal.get(i).getX();
            y = listOfAnimal.get(i).getY();
            dummyMap[y][x] = listOfAnimal.get(i).getRender();
        }

        System.out.println("MAP:");
        for (int i=0; i<10; i++) {
            System.out.print("|");
            for(int j=0; j<11; j++) {
                if (P.getX()==j && P.getY()==i) {
                    System.out.print(P.getRender() + "|");
                }
                else if (dummyMap[i][j]=='\0') {
                    //mixer
                    if (map[i][j].getTypeCell()==1 || map[i][j].getTypeCell()==2 || map[i][j].getTypeCell()==3) {
                        System.out.print(map[i][j].getRender()+"|");
                    }
                    //barn
                else if (map[i][j].getTypeCell()==4) {
                        System.out.print(map[i][j].getRender()+"|");
                    }
                    //coop
                else if (map[i][j].getTypeCell()==5) {
                        System.out.print(map[i][j].getRender()+"|");
                    }
                    //grassland
                else if (map[i][j].getTypeCell()==6) {
                        System.out.print(map[i][j].getRender()+"|");
                    }
                } else {
                    System.out.print(dummyMap[i][j]+"|");
                }
            }
            System.out.println();
        }
        System.out.print("Inventory: ");
        if (P.getBag().getNeff()==0) {
            System.out.println("Kosong");
        } else {
            System.out.println();
            List<Product> bag = P.getBag();
            for (int i=0; i<bag.getNeff(); i++) {
                System.out.println(i+1+". "+bag.get(i).getName());
            }
        }

        System.out.println("Money: "+P.getMoney());
        System.out.println("Water: "+P.getWater());
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
        int i=0;
        try {
            Scanner scanner = new Scanner(new File(namaFile));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int j=0; j<line.length(); j++) {
                    char c = line.charAt(j);
                    switch (c) {
                        case 'c': //coop
                            map[i][j] = new Coop(false);
                            break;
                        case 'g': //grassland
                            map[i][j] = new Grassland(false);
                            break;
                        case 'b': //barn
                            map[i][j] = new Barn(false);
                            break;
                        case 'w': //well
                            map[i][j] = new Well();
                            map[i][j].setOccupied(true);
                            break;
                        case 'm': //mixer
                            map[i][j] = new Mixer();
                            map[i][j].setOccupied(true);
                            break;
                        case 't': //truck
                            map[i][j] = new Truck();
                            map[i][j].setOccupied(true);
                            break;
                        default:
                            System.out.print("error char invalid");
                            break;
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void placeAnimal() {
        boolean berhasil=false, occupied;
        int x, y, typeCell;

        //place angsa
        while (!berhasil) {

            x = (int) (Math.random()*100%11);
            y = (int) (Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==coop)&&(!occupied)) {
                listOfAnimal.add(new Angsa(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place ayam
        while (!berhasil) {

            x = (int) (Math.random()*100%11);
            y = (int) (Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==coop)&&(!occupied)) {
                listOfAnimal.add(new Ayam(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place bebek
        while (!berhasil) {

            x = (int) (Math.random()*100%11);
            y = (int) (Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==coop)&&(!occupied)) {
                listOfAnimal.add(new Bebek(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place kambing
        while (!berhasil) {

            x = (int) (Math.random()*100%11);
            y = (int) (Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==grassland)&&(!occupied)) {
                listOfAnimal.add(new Kambing(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place kuda
        while (!berhasil) {

            x = (int) (Math.random()*100%11);
            y = (int) (Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==grassland)&&(!occupied)) {
                listOfAnimal.add(new Kuda(x,y));
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }

        berhasil=false;
        //place sapi
        while (!berhasil) {

            x = (int) (Math.random()*100%11);
            y = (int) (Math.random()*100%10);

            typeCell = map[y][x].getTypeCell();
            occupied = map[y][x].isOccupied();
            //barn or coop
            if ((typeCell==barn || typeCell==grassland)&&(!occupied)) {
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
            x = (int) (Math.random()*100%11);
            y = (int) (Math.random()*100%10);
            occupied = map[y][x].isOccupied();
            //barn or coop
            if (!occupied) {
                P = new Player(x,y);
                map[y][x].setOccupied(true);
                berhasil = true;
            }
        }
    }

    public void movePlayer(int direction) {
        P.Move(direction, map);
    }

    public void liveAnimal() {
        for (int i=0; i<listOfAnimal.getNeff(); i++) {
            listOfAnimal.get(i).live(map);
        }
    }

    public void clearDeadAnimal() {
        int i=0;
        while (i < listOfAnimal.getNeff()) {
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
        P.Kill(listOfAnimal, map);
    }

    public void playerInteract() {
        P.Interact(listOfAnimal, map, gameTime);
    }

    public Player getPlayer() {
        return P;
    }

    public List<FarmAnimal> getListOfAnimal() {
        return listOfAnimal;
    }

    public List<Product> getListOfProduct() {
        return listOfProduct;
    }

    public Cell[][] getMap() {
        return map;
    }

}
