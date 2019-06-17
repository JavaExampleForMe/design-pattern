package pizzeria.packaging;

import pizzeria.Pizza;

public class BasicPackage implements PackagePizza {


    private Pizza pizza;

    public BasicPackage(Pizza pizza){
                this.pizza = pizza;
    }

    @Override
    public String packPizza() {
        return " packed in basic package";
    }

    @Override
    public double getPrice() {
        return pizza.calcPrice() + 1;
    }
}
