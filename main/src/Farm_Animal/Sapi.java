package src.Farm_Animal;

import src.FarmAnimal;
import src.Product;

import src.Products.FarmProducts.Eggs.*;
import src.Products.FarmProducts.Meats.*;
import src.Products.FarmProducts.Milks.*;

public class Sapi extends FarmAnimal implements EggProducingAnimal, MeatProducingAnimal {
    public Sapi(int _x, int _y){
        super(_x,_y, "Mooo", 6);
        renderChar = 's';
        hungry = 10;
        timeLeft = hungry + 5;
    }
    public void sound(){
        System.out.println(super.getVoice());
    }

    @Override
    public Product extract() {
        hasProduct = false;
        CowMilk A = new CowMilk();
        return A;
    }
}
