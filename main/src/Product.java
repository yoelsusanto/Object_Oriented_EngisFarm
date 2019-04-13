package src;

public class Product {
    //Atribut
    protected final String name;
    protected final int type_product;
    protected final int price;

    //Method
    public Product(String name, int type_product, int price) {
        this.name=name;
        this.type_product=type_product;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public int getTypeProduct() {
        return type_product;
    }

    public int getPrice() {
        return price;
    }
}
