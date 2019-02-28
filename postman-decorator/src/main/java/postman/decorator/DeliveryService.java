package postman.decorator;

import postman.logic.*;

import java.util.function.BiConsumer;

public class DeliveryService extends postman.logic.DeliveryService {

    private Postman postman;

    public DeliveryService(PackagesStorage packagesStorage, DeliveryTask deliveryTask) {
        super(packagesStorage, deliveryTask);
        postman = new PostmanImpl(packagesStorage);
        postman = new SortPostman(postman); // 3027
        postman = new ParallelPostman(postman); // 1105
    }

    public void startWorkDay(){
        packagesStorage.addPackages(deliveryTask.getAllPackages());
        postman.deliver(deliveryTask);
    }

    @Override
    public void registerForPostmanRoute(Observer observer, BiConsumer<String, Address> consumer) {
        postman.addObserver(observer, consumer);
    }

}
