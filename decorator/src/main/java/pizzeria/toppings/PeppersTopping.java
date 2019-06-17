package pizzeria.toppings;

import pizzeria.Pizza;

public class PeppersTopping extends ToppingDecorator {

    private ToppingCoverage toppingCoverage;

    public PeppersTopping(Pizza pizza, ToppingCoverage toppingCoverage) {
        super(pizza);
        this.toppingCoverage = toppingCoverage;
    }

    @Override
    public String bake() {
        return super.bake() + "Peppers";
    }

    @Override
    public double calcPrice() {
        return pizza.calcPrice() + 2 * toppingCoverage.getPart();
    }
}
