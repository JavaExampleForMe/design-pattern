package postman;

import java.util.UUID;

public class PackageInfo {
    private UUID id;
    Address address;
    String name;
    DeliveryStatus status;

    public PackageInfo(Address address, String name) {
        this.address = address;
        this.name = name;
        this.status = DeliveryStatus.New;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PackageInfo{" +
                "Address=" + address +
                ", name='" + name + '\'' +
                '}';
    }
}
