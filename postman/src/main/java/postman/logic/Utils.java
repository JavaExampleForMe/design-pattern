package postman.logic;

import org.fluttercode.datafactory.impl.DataFactory;
import postman.ui.City;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final DataFactory dataFactory = new DataFactory();

    public static List<PackageInfo> createPackageInfos() {
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
        packages.add(new PackageInfo(address, "Mr. "+ dataFactory.getLastName()));
    }
}
