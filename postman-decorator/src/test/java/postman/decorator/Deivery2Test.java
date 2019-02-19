//package postman.decorator;
//
//import org.fluttercode.datafactory.impl.DataFactory;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import postman.Address;
//import postman.City;
//import postman.DeliveryTask2;
//import postman.PackageInfo;
//
//import javax.swing.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static java.lang.Thread.sleep;
//
//public class Deivery2Test {
//    private DataFactory dataFactory;
//
//    @Before
//    public void setUp() throws Exception {
//        dataFactory = new DataFactory();
//    }
//
//    @Test
//    public void simpleDelivery() {
//        postman.decorator.DeliverService deliverService = new postman.decorator.DeliverService(4,10);
//        HashMap<Address, JButton> existingAddresses = deliverService.drawMap();
//
//        List<PackageInfo> packages = new ArrayList<>();
//        List<Address> addresses = new ArrayList<>();
//        addresses.add(new Address(City.London, "street1", 3));
//        addresses.add(new Address(City.Paris, "street2", 10));
//        addresses.add(new Address(City.NY, "street3", 6));
//        addresses.add(new Address(City.Paris, "street2", 5));
//        addresses.add(new Address(City.London, "street2", 1));
//        addresses.add(new Address(City.Rome, "street1", 3));
//        addresses.add(new Address(City.London, "street3", 9));
//        addresses.add(new Address(City.London, "street1", 2));
//        for (Address address : addresses) {
//            addAddressToPackage(existingAddresses, packages, address);
//        }
//
//        final int actualDeliver = deliverService.deliver(new DeliveryTask2() {
//
//
//            @Override
//            public List<PackageInfo> getAllPackages() {
//                return packages;
//            }
//
//
//        });
//        System.out.println("Total time "  + actualDeliver);
//        try {
//            sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Assert.assertEquals(320,actualDeliver);
//    }
//    private void addAddressToPackage(HashMap<Address, JButton> existingAddresses, List<PackageInfo> packages, Address address) {
//        packages.add(new PackageInfo(((JButton) existingAddresses.get(address)).getLocationOnScreen(), address, "Mr. "+ dataFactory.getLastName()));
//    }
//
//}
