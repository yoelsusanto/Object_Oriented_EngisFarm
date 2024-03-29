package src.Farm_Animal;

import src.FarmAnimal;
import src.Product;

import src.Products.FarmProducts.Eggs.*;
import src.Products.FarmProducts.Meats.*;
import src.Products.FarmProducts.Milks.*;

public class Kambing extends FarmAnimal implements MilkProducingAnimal, MeatProducingAnimal {
    public Kambing(int _x, int _y){
        super(_x,_y, "Mbeek", 4);
        renderChar = 'k';
        hungry = 8;
        timeLeft = hungry + 5;
    }
    public void sound(){
        System.out.println(super.getVoice());
    }

    @Override
    public Product extract() {
        hasProduct = false;
        GoatMilk A = new GoatMilk();
        return A;
    }
}
