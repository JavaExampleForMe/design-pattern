package pizzeria.toppings;

import pizzeria.Pizza;

public abstract class ToppingDecorator implements Pizza {
    Pizza pizza;
    public ToppingDecorator(Pizza newPizza) {
        this.pizza = newPizza;
    }

    public String bakePizza() {
        return this.pizza.bakePizza() + " with ";
    }

    public double getPrice() {
        return this.pizza.getPrice();
    }
}
