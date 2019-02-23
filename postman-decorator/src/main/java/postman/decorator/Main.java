package postman.decorator;

import org.fluttercode.datafactory.impl.DataFactory;
import postman.Address;
import postman.DeliveryTask;
import postman.PackageInfo;
import postman.ProductionStorage;
import postman.UI.City;
import postman.UI.FrameMap;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String [ ] args) {
        ProductionStorage packagesStorage = new ProductionStorage();
        List<PackageInfo> packages = createPackageInfos();
        DeliveryTask deliveryTask = new DeliveryTask(){
            @Override
            public List<PackageInfo> getAllPackages() {
                return packages;
            }
        };
        postman.decorator.DeliveryService deliveryService = new postman.decorator.DeliveryService(packagesStorage, deliveryTask);
        FrameMap frameMap = new FrameMap(deliveryService, 4, 10);
        frameMap.drawMap();
        //Act
        deliveryService.startWorkDay();

    }

    private static List<PackageInfo> createPackageInfos() {
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
    private static void addAddressToPackage(List<PackageInfo> packages, Address address) {
        DataFactory dataFactory = new DataFactory();
        packages.add(new PackageInfo(address, "Mr. "+ dataFactory.getLastName()));
    }
}
