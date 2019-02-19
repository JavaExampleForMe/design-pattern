package postman;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryStorage implements DeliveryTasksStorage {
    private List<DeliveryTask2> tasks = new ArrayList<>();

    @Override
    //Change for Stav
    public void addDeliveryTask(DeliveryTask2 deliveryTask) {
        List<PackageInfo> packages = deliveryTask.getAllPackages();
        for (PackageInfo packageInfo : packages) {
            UUID uuid = UUID.randomUUID();
            packageInfo.setId(uuid);
        }
        tasks.add(deliveryTask);

    }

    @Override
    public DeliveryTask2 getDeliveryTask() {
        return tasks.get(0);
    }

    @Override
    public void markPackageAsDelivered(PackageInfo packageInfo) {
        DeliveryTask2 deliveryTask2 = tasks.get(0);
        List<PackageInfo> packages = deliveryTask2.getAllPackages();
        for (PackageInfo aPackage : packages) {
            aPackage.setStatus("Delivered");
        }


    }
}
