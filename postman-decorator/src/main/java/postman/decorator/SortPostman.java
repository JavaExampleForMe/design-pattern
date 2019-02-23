package postman.decorator;

import postman.*;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class SortPostman implements Postman {

    private Postman postman;

    public SortPostman(Postman postman) {
        this.postman = postman;
    }

    @Override
    public int deliver(DeliveryTask deliveryTask) {
        DeliveryTask newDeliveryTask = new DeliveryTask() {
            @Override
            public List<PackageInfo> getAllPackages() {
                Comparator<PackageInfo> comp = new Comparator<PackageInfo>() {
                    @Override
                    public int compare(PackageInfo obj1, PackageInfo obj2) {
                        return obj1.getAddress().compareTo(obj2.getAddress());
                    }
                } ;

                return deliveryTask.getAllPackages().stream().sorted(comp).collect(Collectors.toList());
            }
        };
        return postman.deliver(newDeliveryTask);
    }

    @Override
    public void addObserver(Observer observer, BiConsumer<String,Address> consumer) {
        postman.addObserver(observer, consumer);
    }

    @Override
    public void removeObserver(Observer observer) {
        postman.removeObserver(observer);
    }

    @Override
    public void notifyObserver(String addressee, Address toAddress) {
        postman.notifyObserver(addressee, toAddress);
    }
}
