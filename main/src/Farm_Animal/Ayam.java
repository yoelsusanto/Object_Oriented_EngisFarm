package src.Farm_Animal;

import src.FarmAnimal;
import src.Product;

import src.Products.FarmProducts.Eggs.*;
import src.Products.FarmProducts.Meats.*;
import src.Products.FarmProducts.Milks.*;

public class Ayam extends FarmAnimal implements EggProducingAnimal, MeatProducingAnimal {
    public Ayam(int _x, int _y){
        super(_x,_y, "Petook", 2);
        renderChar = 'c';
        hungry =6;
        timeLeft = hungry + 5;
    }
    public void sound(){
        System.out.println(super.getVoice());
    }

    @Override
    public Product extract() {
        hasProduct = false;
        ChickenEgg A = new ChickenEgg();
        return A;
    }
}
