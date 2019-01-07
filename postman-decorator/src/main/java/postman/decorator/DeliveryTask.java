package postman.decorator;

import java.util.List;

public interface DeliveryTask {
    int[] getCitiesIds();
    List<PackageInfo> getAllPackages();
    String[] getStreet(int cityId);
}
