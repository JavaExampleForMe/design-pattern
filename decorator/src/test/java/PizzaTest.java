import org.junit.Assert;
import org.junit.Test;
import pizzeria.ThinCrustPizza;
import pizzeria.Pizza;
import pizzeria.packaging.PackagePizza;
import pizzeria.packaging.PremiumPackage;
import pizzeria.toppings.MushroomsTopping;
import pizzeria.toppings.OlivesTopping;

public class PizzaTest {
    @Test
    public void basicPrice() {
        Pizza pizza = new ThinCrustPizza();
        Assert.assertEquals(10 , pizza.calcPrice(),0);
        System.out.println(pizza.bake());
    }

    @Test
    public void pizzaWithOneToppic() {
        Pizza pizza = new ThinCrustPizza();
        pizza = new OlivesTopping(pizza);
        Assert.assertEquals(12 , pizza.calcPrice(),0);
        System.out.println(pizza.bake());
    }

    @Test
    public void pizzaWithMultiToppic() {
        Pizza pizza = new ThinCrustPizza();
        pizza = new OlivesTopping(pizza);
        pizza = new MushroomsTopping(pizza);
        Assert.assertEquals(15 , pizza.calcPrice(),0);
        System.out.println(pizza.bake());
    }

    @Test
    public void pizzaWithPackagingToppic() {
        Pizza pizza = new ThinCrustPizza();
        pizza = new OlivesTopping(pizza);
        PackagePizza packagePizza = new PremiumPackage(pizza);
        Assert.assertEquals(12 , packagePizza.getPrice(),0);
        System.out.println(packagePizza.packPizza());
    }

}