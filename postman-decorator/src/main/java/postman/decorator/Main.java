package postman.decorator;

import postman.logic.*;
import postman.ui.FrameMap;

import java.util.List;

public class Main {
    public static void main(String [ ] args) {
        ProductionStorage packagesStorage = new ProductionStorage();
        List<PackageInfo> packages = Utils.createPackageInfos();
        DeliveryTask deliveryTask = () -> packages;
        DeliveryService deliveryService = new DeliveryService(packagesStorage, deliveryTask);
        Postman postman = new PostmanImpl(packagesStorage);
        postman = new SortPostman(postman);
        postman = new ParallelPostman(postman);
        deliveryService.setPostman(postman);
        FrameMap frameMap = new FrameMap(deliveryService, 4, 10);
        frameMap.drawMap();
        //Act
        deliveryService.startWorkDay();

    }

}
