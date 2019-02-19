package postman;

public class DeliveryTasksManager {
    private DeliveryTask deliveryTask;

    private int currentTaskIndex = 0;
    private DeliveryTasksStorage deliveryTasksStorage;

    public DeliveryTasksManager(DeliveryTasksStorage deliveryTasksStorage) {
        this.deliveryTasksStorage = deliveryTasksStorage;
    }


    public void setDeliveryTasks(DeliveryTask2 deliveryTask) {
        deliveryTasksStorage.addDeliveryTask(deliveryTask);
    }

    public DeliveryTask2 getNextTask() {
        DeliveryTask2 deliveryTask2 = deliveryTasksStorage.getDeliveryTask();
        return deliveryTask2;
    }

    public void packagedDelivered(PackageInfo packageInfo) {
        deliveryTasksStorage.markPackageAsDelivered(packageInfo);

    }
}
