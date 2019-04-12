package src.Farm_Animal;

import src.FarmAnimal;
import src.Product;

import src.Products.FarmProducts.Eggs.*;
import src.Products.FarmProducts.Meats.*;
import src.Products.FarmProducts.Milks.*;

public class Kuda extends FarmAnimal implements MilkProducingAnimal, MeatProducingAnimal {
    public Kuda(int _x, int _y){
        super(_x,_y, "Hikhik-hikhik", 5);
        renderChar = 'h';
        hungry = 9;
        timeLeft = hungry + 5;
    }
    public void sound(){
        System.out.println(super.getVoice());
    }

    @Override
    public Product extract() {
        hasProduct = false;
        HorseMilk A = new HorseMilk();
        return A;
    }
}
