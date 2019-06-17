package pizzeria;

public class ThinCrustPizza implements Pizza {
    public String bake() {
        return "Thin Pizza";
    }

    public double calcPrice() {
        return 10;
    }
}
