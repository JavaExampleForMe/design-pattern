package postman;

import java.awt.*;

public class PackageInfo {
    Address address;
    Point addressPoint;
    String name;

    public PackageInfo(Point addressPoint, Address address, String name) {
        this.address = address;
        System.out.println(this.toString());
        this.addressPoint = addressPoint;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PackageInfo{" +
                "Address=" + address +
                ", name='" + name + '\'' +
                '}';
    }

    public Address getAddress() {
        return address;
    }
}
