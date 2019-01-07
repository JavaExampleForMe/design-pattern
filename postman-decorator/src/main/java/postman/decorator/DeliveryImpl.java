package postman.decorator;

public class DeliveryImpl implements Delivery {
    @Override
    public int deliver(DeliveryTask deliveryTask) {
        System.out.println(Thread.currentThread().getName() + ": Delivered  Plan for today :");
        int totalTime = 0;
        int lastCityId =0;
        String lastStreet = "";
        int lastNumber =0;
        for (PackageInfo currPackage : deliveryTask.getAllPackages()) {
            if (lastCityId != currPackage.cityId)
                    totalTime += 60;
            else if (!lastStreet.equals(currPackage.street))
                totalTime += 30;
            else
                totalTime += Math.abs(lastNumber-currPackage.number);
            lastCityId = currPackage.cityId;
            lastStreet = currPackage.street;
            lastNumber = currPackage.number;
            System.out.println(Thread.currentThread().getName() + ": delivered "+ currPackage + " total time " + totalTime);
        }
        return totalTime;
    }
}
