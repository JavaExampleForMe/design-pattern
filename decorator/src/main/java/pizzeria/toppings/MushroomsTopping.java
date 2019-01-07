package pizzeria.toppings;

import pizzeria.Pizza;

public class MushroomsTopping extends ToppingDecorator {
    public MushroomsTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String bakePizza() {
        return super.bakePizza() + "Mushrooms";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 3;
    }
}
