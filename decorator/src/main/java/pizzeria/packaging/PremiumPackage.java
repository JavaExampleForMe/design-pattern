package pizzeria.packaging;

import pizzeria.Pizza;

public class PremiumPackage implements PackagePizza {

    private Pizza pizza;

    public PremiumPackage(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String packPizza() {
        return " packed in premium package";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 0;
    }
}
