package postman;

import java.util.List;

public interface DeliveryTask {
    City[] getCities();
    List<PackageInfo> getAllPackages();
    String[] getStreet(City city);
}
