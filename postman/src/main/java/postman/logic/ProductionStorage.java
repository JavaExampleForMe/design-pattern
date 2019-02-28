package postman.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductionStorage implements PackagesStorage {
    private List<PackageInfo> packagesStorage = new ArrayList<>();

    @Override
    //Change for Stav
    public void addPackages(List<PackageInfo> packages) {
        for (PackageInfo packageInfo : packages) {
            UUID uuid = UUID.randomUUID();
            packageInfo.setId(uuid);
            packagesStorage.add(packageInfo);
        }

    }

    @Override
    public void markPackageAsDelivered(PackageInfo packageInfo) {
        for (PackageInfo aPackage : packagesStorage) {
            if (aPackage.getId() == packageInfo.getId()) {
                aPackage.setStatus(DeliveryStatus.Delivered);
            }
        }


    }
}
