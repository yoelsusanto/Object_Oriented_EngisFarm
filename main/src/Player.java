package src;

import src.Products.FarmProducts.Meats.*;
import java.util.*;

import org.apache.commons.lang3.mutable.MutableInt;
import src.Cells.Facilities.*;

public class Player extends Renderable {

    // attirbutes
    private int x,y;
    private MutableInt WaterContainer;
    private int direction;
    private List<Product> Bag;
    private MutableInt money;

    // methods
    public Player() {

    }

    public Player(int x, int y) { // ctor player
        Bag = new List<Product>();
        money = new MutableInt(0);
        WaterContainer = new MutableInt(100);
        this.x = x;
        this.y = y;
        renderChar = '^';
        direction = 0;
    }

    // ctor user-defined player, inisialisasi
    public Player(int n, Product P) {

    }

    // find tile apa yang ada disekitar
    // return 1 jika facility
    // return 2 jika land
    public int find(int x, int y, List<FarmAnimal> listOfAnimal) {
        int xAnimal, yAnimal;
        for (int i = 0; i < listOfAnimal.getNeff(); i++) {
            xAnimal = listOfAnimal.get(i).getX();
            yAnimal = listOfAnimal.get(i).getY();
            if (x==xAnimal && y==yAnimal) {
                return i;
            }
        }
        return -1;
    }

    public int getMoney() {
        return money.intValue();
    }

    public void setMoney(int newVal) {
        money.setValue(newVal);
    }

    public int getWater() {
        return WaterContainer.intValue();
    }

    public void setWater(int water) {
        WaterContainer.setValue(water);
    }

    public void Talk(List<FarmAnimal> listOfAnimal) {
        int targetX, targetY, typeCell, i1, i2;
        targetX = x;
        targetY = y;

        //atas
        if (direction==0) {
            targetY--;
        }
        //kanan
        else if (direction == 1) {
            targetX++;
        }
        //bawah
        else if (direction == 2) {
            targetY++;
        }
        //kiri
        else {
            targetX--;
        }
        // ada hewan
        // cout << "Target: " << x << " " << y << endl;
        int idxHewan = find(targetX,targetY,listOfAnimal);
        if (idxHewan!=-1) {
            listOfAnimal.get(idxHewan).sound();
        } else {
            MainFrame.getInstance().setOutputResLabel("Tidak ada animal didepan mata");

//            System.out.println("Tidak ada animal didepan mata");
//            System.out.println(targetX + " " + targetY);
        }
    }

    // Berinteraksi dengan benda di samping player (FarmAnimal & Facility)
    public void Interact(List<FarmAnimal> listOfAnimal, Cell[][] map, int gameTime) {
        int targetX, targetY, typeCell, i1, i2;
        targetX = x;
        targetY = y;

        //atas
        if (direction==0) {
            targetY--;
        }
        //kanan
        else if (direction == 1) {
            targetX++;
        }
        //bawah
        else if (direction == 2) {
            targetY++;
        }
        //kiri
        else {
            targetX--;
        }

        //jika target adalah tanah kosong
        if (!map[targetY][targetX].isOccupied()) {
            System.out.println("Can't interact");
        }
        //adalah binatang atau facility
        else {
            typeCell = map[targetY][targetX].getTypeCell();
            if (typeCell<4) { //facility
                if (typeCell==1) {
                    Mixer pM = (Mixer) (map[targetY][targetX]);
                    System.out.println("Masukkan index barang: ");
                    Scanner in = new Scanner(System.in);
                    i1 = in.nextInt();
                    i2 = in.nextInt();
                    pM.Mix(i1,i2, Bag);
                } else if (typeCell==2) {
                    Truck pT = (Truck) (map[targetY][targetX]);
                    pT.sellProduct(Bag, money, gameTime);
                } else if (typeCell==3) {
                    Well pW = (Well) (map[targetY][targetX]);
                    pW.getWater(WaterContainer);

                    System.out.println("Berhasil mendapatkan air!");
                }
            } else { //land, berarti interaksi dengan binatang
                int indexAnimal = find(targetX, targetY, listOfAnimal);
                if (listOfAnimal.get(indexAnimal).getHasProduct()) {
                    Bag.add(listOfAnimal.get(indexAnimal).extract());
                } else {
                    System.out.println("Animal tidak memiliki produk!");
                }
            }
        }
    }

    // Menggerakkan player
    public void Move(int dir, Cell [][] map) {
        // move
        if (dir == 0 && isPointValid(y-1,x) && !map[y-1][x].isOccupied()) { // up
            map[y][x].setOccupied(false);
            y--;
            map[y][x].setOccupied(true);
        }
        else if (dir == 1 && isPointValid(y,x+1) && !map[y][x+1].isOccupied()) { // right
            map[y][x].setOccupied(false);
            x++;
            map[y][x].setOccupied(true);
        }
        else if (dir == 2 && isPointValid(y+1,x) && !map[y+1][x].isOccupied()) { // down
            map[y][x].setOccupied(false);
            y++;
            map[y][x].setOccupied(true);
        }
        else if (dir == 3 && isPointValid(y,x-1) && !map[y][x-1].isOccupied()) { // left
            map[y][x].setOccupied(false);
            x--;
            map[y][x].setOccupied(true);
        }

        // facing
        changeDirection(dir);
    }

    // Menyembelih hewan, bila animalnya termasuk yang meatProducer
    public void Kill(List<FarmAnimal> listOfAnimal, Cell map [][]) {
        int targetX, targetY, typeCell, i1, i2;
        targetX = x;
        targetY = y;

        //atas
        if (direction==0) {
            targetY--;
        }
        //kanan
        else if (direction == 1) {
            targetX++;
        }
        //bawah
        else if (direction == 2) {
            targetY++;
        }
        //kiri
        else {
            targetX--;
        }

        int idxHewan = find(targetX,targetY, listOfAnimal);
        if (idxHewan!=-1) {
            int typeHewan = (listOfAnimal).get(idxHewan).getTypeAnimal();


            // delete (*listOfAnimal).get(idxHewan);
            map[targetY][targetX].setOccupied(false);

            (listOfAnimal).removeIdx(idxHewan);

            //angsa
            if (typeHewan==1) {
                Bag.add(new GooseMeat());
            }//ayam
            else if (typeHewan==2) {
                Bag.add(new ChickenMeat());
            }
            else if (typeHewan==3) {
                Bag.add(new DuckMeat());
            }
            else if (typeHewan==4) {
                Bag.add(new GoatMeat());
            }
            else if (typeHewan==5) {
                Bag.add(new HorseMeat());
            }
            else if (typeHewan==6) {
                Bag.add(new CowMeat());
            }
            System.out.println("Berhasil memasukkan product");

        } else {
            MainFrame.getInstance().setOutputResLabel("Tidak ada animal didepan mata");
        }
    }

    // Menyiram land dengan wadah air yang dimiliki
    public void Grow(Cell [][] map) {
        if (!map[y][x].isHasGrass()) {
            if (WaterContainer.intValue()>=10) {
                map[y][x].setHasGrass(true);
                WaterContainer.setValue(WaterContainer.intValue()-10);
                System.out.println("Grass sudah ditumbuhkan");
            } else {
                System.out.println("Water tidak cukup");
            }
        } else {
            System.out.println("Grass masih ada");
        }
    }

    // dan menumbuhkan rumput di petak tersebut
    public List<Product> getBag() {
        return Bag;
    }

    //bagian player yang berfungsi untuk menerima command
    public void ReceiveCommand() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPointValid(int _y, int _x) {
        return (_y >= 0 && _y < 10 && _x >= 0 && _x < 11);
    }

    public void changeDirection(int direction) {
        //atas
        if (direction==0) {
            renderChar = '^';
        }
        //kanan
        else if (direction==1) {
            renderChar = '>';
        }
        //bawah
        else if (direction==2) {
            renderChar = 'v';
        }
        //kiri
        else {
            renderChar = '<';
        }
        this.direction=direction;
    }

}