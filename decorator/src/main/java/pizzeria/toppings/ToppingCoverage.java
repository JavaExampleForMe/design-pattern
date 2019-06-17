package pizzeria.toppings;

public enum ToppingCoverage {
    LEFT (0.5),
    RIGHT (0.5),
    FULL(1.0);

    private final double part;

    ToppingCoverage(double part) {
        this.part = part;
    }

    public double getPart() {
        return part;
    }
}
