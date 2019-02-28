package postman;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import postman.logic.Address;
import postman.logic.DeliveryService;
import postman.logic.DeliveryTask;
import postman.logic.PackageInfo;
import postman.ui.City;

import java.util.ArrayList;
import java.util.List;

public class PostmanTest {

    private DataFactory dataFactory;

    @Before
    public void setUp()  {
        dataFactory = new DataFactory();
    }

    @Test
    public void simpleDelivery() {
        //Arrange
        InMemoryStorage deliveryTasksStorage = new InMemoryStorage();
        List<PackageInfo> packages = createPackageInfos();
        DeliveryTask deliveryTask = new DeliveryTask(){
            @Override
            public List<PackageInfo> getAllPackages() {
                return packages;
            }
        };
        DeliveryService deliveryService = new DeliveryService(deliveryTasksStorage, deliveryTask);

        //Act
        deliveryService.startWorkDay();

        List<PackageInfo> packagesAfterUpdates = deliveryTask.getAllPackages();
        for (PackageInfo packagesAfterUpdate : packagesAfterUpdates) {
                    Assert.assertEquals("Delivered",packagesAfterUpdate.getStatus());
        }


    }

    private List<PackageInfo> createPackageInfos() {
        List<PackageInfo> packages = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(City.London, "street1", 3));
        addresses.add(new Address(City.Paris, "street2", 10));
        addresses.add(new Address(City.NY, "street3", 6));
        addresses.add(new Address(City.Paris, "street2", 5));
        addresses.add(new Address(City.London, "street2", 1));
        addresses.add(new Address(City.Rome, "street1", 3));
        addresses.add(new Address(City.London, "street3", 9));
        addresses.add(new Address(City.London, "street1", 2));
        for (Address address : addresses) {
            addAddressToPackage( packages, address);
        }
        return packages;
    }

    private void addAddressToPackage(List<PackageInfo> packages, Address address) {
        packages.add(new PackageInfo(address, "Mr. "+ dataFactory.getLastName()));
    }
}
