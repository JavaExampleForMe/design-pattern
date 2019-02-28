package postman.logic;

import java.util.function.BiConsumer;

public class DeliveryService{

    protected final PackagesStorage packagesStorage;
    private PostmanImpl postman;
    protected DeliveryTask deliveryTask;

    public DeliveryService(PackagesStorage packagesStorage, DeliveryTask deliveryTask) {
        this.packagesStorage = packagesStorage;
        postman = new PostmanImpl(this.packagesStorage);
        this.deliveryTask = deliveryTask;
    }

    public void startWorkDay(){
        packagesStorage.addPackages(deliveryTask.getAllPackages());
        postman.deliver(deliveryTask);
    }

    public void registerForPostmanRoute(Observer observer, BiConsumer<String, Address> consumer) {
        postman.addObserver(observer, consumer);
    }

}
