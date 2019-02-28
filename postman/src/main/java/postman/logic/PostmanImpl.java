package postman.logic;

import lombok.SneakyThrows;
import postman.ui.City;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class PostmanImpl implements Postman {

    private PackagesStorage packagesStorage;
    private Address currentAddress =  new Address(City.Rome, "", 0);;
    private Map<Observer, BiConsumer<String,Address>> observers = new HashMap<>();

    public PostmanImpl(PackagesStorage packagesStorage) {
        this.packagesStorage = packagesStorage;
    }

    @SneakyThrows
    @Override
    public int deliver(DeliveryTask deliveryTask) {
        int totalTime = 0;
        for (PackageInfo currPackage : deliveryTask.getAllPackages()) {
            int  millis = calcRoutTime(currPackage.address);
            Thread.sleep(millis);
            notifyObserver(currPackage.name, currPackage.address);
            currentAddress = currPackage.address;
            System.out.println(Thread.currentThread().getName() + ": delivered "+ currPackage + " total time " + totalTime);
            packagesStorage.markPackageAsDelivered(currPackage);
            totalTime += millis;
        }
        return totalTime;
    }

    private int calcRoutTime(Address destination) {
        int currTime = 0;
        if (cityHasChanged(currentAddress, destination)){
            currTime += 90;
        }
        else if (streetHasChanged(currentAddress, destination)){
            currTime += 30 + Math.abs(currentAddress.house-destination.house);;
        }
        else{
            currTime += Math.abs(currentAddress.house-destination.house);
        }
        return currTime;
    }

    private boolean streetHasChanged(Address prevAddress, Address currAddress) {
        return !prevAddress.street.equals(currAddress.street);
    }

    private boolean cityHasChanged(Address prevAddress, Address currAddress) {
        return prevAddress.city != currAddress.city;
    }

    @Override
    public void addObserver(Observer observer, BiConsumer<String,Address> consumer) {
        observers.putIfAbsent(observer, consumer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String addressee, Address toAddress) {
        for (BiConsumer<String,Address> observer : observers.values()) {
            observer.accept(addressee, toAddress);
        }

    }

}
