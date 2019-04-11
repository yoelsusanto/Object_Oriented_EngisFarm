package src.Farm_Animal;

import src.FarmAnimal;
import src.Products.Product;

public class Sapi extends FarmAnimal implements EggProducingAnimal, MeatProducingAnimal {
    public Sapi(int _x, int _y){
        super(_x,_y, "Mooo", 5);
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
