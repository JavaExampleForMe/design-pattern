package pizzeria;

public class ThickCrustPizza implements Pizza {
    public String bake() {
        return "Thick Pizza";
    }

    public double calcPrice() {
        return 15;
    }
}
