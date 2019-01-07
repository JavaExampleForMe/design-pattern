package postman.decorator;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeiveryTest {
    private DataFactory dataFactory;

    @Before
    public void setUp() throws Exception {
        dataFactory = new DataFactory();
    }

    @Test
    public void simpleDelivery() {
        List<PackageInfo> packages = new ArrayList<>();
        packages.add(new PackageInfo(1, "City1Street1", 12, dataFactory.getLastName()));
        packages.add(new PackageInfo(2, "City2Street4", 10, dataFactory.getLastName()));
        packages.add(new PackageInfo(2, "City2Street4", 5, dataFactory.getLastName()));
        packages.add(new PackageInfo(1, "City1Street2", 27, dataFactory.getLastName()));
        packages.add(new PackageInfo(3, "City3Street1", 3, dataFactory.getLastName()));
        packages.add(new PackageInfo(1, "City1Street3", 9, dataFactory.getLastName()));
        packages.add(new PackageInfo(1, "City1Street1", 18, dataFactory.getLastName()));

        Delivery delivery = new DeliveryImpl(); // 335
        delivery = new SortDelivery(delivery); // 251
        delivery = new ParallelDelivery(delivery, 4); // 126

        final int actualDeliver = delivery.deliver(new DeliveryTask() {
            @Override
            public int[] getCitiesIds() {
                return packages.stream().mapToInt(PackageInfo::getCityId).distinct().toArray();
            }

            @Override
            public List<PackageInfo> getAllPackages() {
                return packages;
            }

            @Override
            public String[] getStreet(int cityId) {
                return packages.stream()
                        .filter(packageInfo -> packageInfo.cityId == cityId)
                        .map(PackageInfo::getStreet)
                        .distinct()
                        .toArray(String[]::new);
            }
        });
        System.out.println("Total time "  + actualDeliver);
        Assert.assertEquals(320,actualDeliver);
    }

}
