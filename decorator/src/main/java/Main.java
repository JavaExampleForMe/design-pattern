import pizzeria.ThinCrustPizza;
import pizzeria.Pizza;
import pizzeria.packaging.PremiumPackage;
import pizzeria.toppings.MushroomsTopping;
import pizzeria.toppings.OlivesTopping;
import pizzeria.toppings.ToppingCoverage;

public class Main {
    public static void main(String[] args) {

        Pizza pizza = new ThinCrustPizza() ;
        pizza = new OlivesTopping(pizza, ToppingCoverage.LEFT);
        pizza = new MushroomsTopping(pizza, ToppingCoverage.RIGHT);

        PremiumPackage premiumPackage = new PremiumPackage(pizza);
        System.out.println("The price is $" + premiumPackage.getPrice()); // Display the string.
    }
}
