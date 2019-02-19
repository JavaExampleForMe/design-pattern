package postman;

public class PostmanImpl implements Postman {
    private Address currentLocation =  new Address(City.Rome, "", 0);;
    private DeliveryTasksManager deliveryTasksManager;

    public PostmanImpl(DeliveryTasksManager deliveryTasksManager) {
        this.deliveryTasksManager = deliveryTasksManager;
    }

    @Override
    public int deliver() {
        int totalTime = 0;


        DeliveryTask2 nextTask = deliveryTasksManager.getNextTask();
        for (PackageInfo currPackage : nextTask.getAllPackages()) {

            try {
                Thread.sleep(calcRoutTime(currPackage.address));
            }
            catch (InterruptedException e) {

            }
            currentLocation = currPackage.address;
            System.out.println(Thread.currentThread().getName() + ": delivered "+ currPackage + " total time " + totalTime);
            deliveryTasksManager.packagedDelivered(currPackage);
        }
        return totalTime;
    }

    private int calcRoutTime(Address destination) {
        int currTime = 0;
        if (cityHasChanged(currentLocation, destination)){
            currTime += 90;
        }
        else if (streetHasChanged(currentLocation, destination)){
            currTime += 30 + Math.abs(currentLocation.house-destination.house);;
        }
        else{
            currTime += Math.abs(currentLocation.house-destination.house);
        }
        return currTime;
    }

    private boolean streetHasChanged(Address prevAddress, Address currAddress) {
        return !prevAddress.street.equals(currAddress.street);
    }

    private boolean cityHasChanged(Address prevAddress, Address currAddress) {
        return prevAddress.city != currAddress.city;
    }

}
