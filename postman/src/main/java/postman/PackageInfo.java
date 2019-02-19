package postman;

import java.util.UUID;

public class PackageInfo {
    Address address;
    String name;
    private UUID id;
    String status;

    public PackageInfo(Address address, String name) {
        this.address = address;
        System.out.println(this.toString());
        this.name = name;
        this.status = "New";
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

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
