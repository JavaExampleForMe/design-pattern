package postman;

import java.util.List;

public interface DeliveryTask extends List<DeliveryTask> {
    City[] getCities();
    List<PackageInfo> getAllPackages();
    String[] getStreet(City city);
}
