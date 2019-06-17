package pizzeria.toppings;

import pizzeria.Pizza;

public abstract class ToppingDecorator implements Pizza {
    Pizza pizza;
    public ToppingDecorator(Pizza newPizza) {
        this.pizza = newPizza;
    }

    public String bake() {
        return this.pizza.bake() + " with ";
    }

    public double calcPrice() {
        return this.pizza.calcPrice();
    }
}
