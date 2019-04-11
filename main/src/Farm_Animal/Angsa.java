package src.Farm_Animal;

import src.FarmAnimal;
import src.Products.Product;

public class Angsa extends FarmAnimal implements EggProducingAnimal, MeatProducingAnimal {
    public Angsa(int _x, int _y){
        super(_x,_y, "Ngok ngok ngok", 1);
        renderChar = 'a';
        hungry = 5;
        timeLeft = hungry + 5;
    }
    public void sound(){
        System.out.println(super.getVoice());
    }

    @Override
    public Product extract() {
        hasProduct = false;
        GooseEgg A = new GooseEgg();
        return A;
    }
}
