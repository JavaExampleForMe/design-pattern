package postman.logic;

import java.util.List;

public interface PackagesStorage {
    void addPackages(List<PackageInfo> packages);
    void markPackageAsDelivered(PackageInfo packageInfo);
}
