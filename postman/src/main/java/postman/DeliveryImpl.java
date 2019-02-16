package postman;

import java.awt.*;

public class DeliveryImpl implements Delivery {

    private FrameMap frameMap;

    public DeliveryImpl(FrameMap frameMap) {
        this.frameMap = frameMap;
    }

    @Override
    public int deliver(DeliveryTask deliveryTask) {
        int totalTime = 0;
        Address prevAddress = new Address(City.Rome, "", 0);
        Point prevPoint = new Point(0,0);
        for (PackageInfo currPackage : deliveryTask.getAllPackages()) {
            frameMap.getPackageButton().setText("<html>"+currPackage.address.display()+"<br>"+currPackage.name+"</html>" );
            prevPoint = frameMap.getPostmanRout().sendPostman(prevPoint, currPackage.addressPoint, frameMap.getClockLabel());
            int currTime = calcTime(prevAddress, currPackage);
            totalTime = currTime + totalTime;
            prevAddress = currPackage.address;
            System.out.println(Thread.currentThread().getName() + ": delivered "+ currPackage + " total time " + totalTime);
        }
        return totalTime;
    }

    private int calcTime(Address prevAddress, PackageInfo currPackage) {
        int currTime = 0;
        if (cityHasChanged(prevAddress, currPackage.address)){
            currTime += 60;
        }
        else if (streetHasChanged(prevAddress, currPackage.address)){
            currTime += 30;
        }
        else{
            currTime += Math.abs(prevAddress.house-currPackage.address.house);
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
