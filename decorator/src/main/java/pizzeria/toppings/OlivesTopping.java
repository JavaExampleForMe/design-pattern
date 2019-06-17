package pizzeria.toppings;

import pizzeria.Pizza;

public class OlivesTopping extends ToppingDecorator {

    private ToppingCoverage toppingCoverage;

    public OlivesTopping(Pizza pizza, ToppingCoverage toppingCoverage) {
        super(pizza);
        this.toppingCoverage = toppingCoverage;
    }

    @Override
    public String bake() {
        return super.bake() + "Olives";
    }

    @Override
    public double calcPrice() {
        return pizza.calcPrice() + 2 * toppingCoverage.getPart();
    }
}
