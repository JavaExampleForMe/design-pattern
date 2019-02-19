package postman;

public interface DeliveryTasksStorage {
    void addDeliveryTask(DeliveryTask2 deliveryTask);

    DeliveryTask2 getDeliveryTask();

    void markPackageAsDelivered(PackageInfo packageInfo);
}
