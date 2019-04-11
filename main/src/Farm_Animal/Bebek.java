package src.Farm_Animal;

import src.FarmAnimal;
import src.Products.Product;

public class Bebek extends FarmAnimal implements EggProducingAnimal, MeatProducingAnimal {
    public Bebek(int _x, int _y){
        super(_x,_y, "Kwek kwek kwek", 3);
        renderChar = 'b';
        hungry = 7;
        timeLeft = hungry + 5;
    }
    public void sound(){
        System.out.println(super.getVoice());
    }

    @Override
    public Product extract() {
        hasProduct = false;
        DuckEgg A = new DuckEgg();
        return A;
    }
}
