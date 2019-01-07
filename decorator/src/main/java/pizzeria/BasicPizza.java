package pizzeria;

public class BasicPizza implements Pizza {
    public String bakePizza() {
        return "Pizza";
    }

    public double getPrice() {
        return 10;
    }
}
