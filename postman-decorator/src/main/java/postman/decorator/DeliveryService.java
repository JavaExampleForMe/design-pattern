package postman.decorator;

import postman.*;

import java.util.function.BiConsumer;

public class DeliveryService extends postman.DeliveryService {

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
    public void addObserver(Observer observer, BiConsumer<String, Address> consumer) {
        postman.addObserver(observer, consumer);
    }

    @Override
    public void removeObserver(Observer observer) {
        postman.removeObserver(observer);
    }

    @Override
    public void notifyObserver(String fromAddress, Address toAddress) {
        postman.notifyObserver(fromAddress, toAddress);
    }
}
