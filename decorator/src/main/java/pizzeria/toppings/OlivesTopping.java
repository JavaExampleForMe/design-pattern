package pizzeria.toppings;

import pizzeria.Pizza;

public class OlivesTopping extends ToppingDecorator {

    public OlivesTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String bakePizza() {
        return super.bakePizza() + "Olives";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 2;
    }
}
