package postman.logic;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.fluttercode.datafactory.impl.DataFactory;
import postman.ui.City;
import postman.ui.FrameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final DataFactory dataFactory = new DataFactory();

    public static void main(String [ ] args) {
//        Map<City, List<Triple<String, Integer, Integer>>> existingAddresses = new HashMap<City, List<Triple<String, Integer, Integer>>>();
//        for (City city : City.values()) {
//            List<Triple<String, Integer, Integer>> cityStreets = new ArrayList<>();
//            for (int i = 0; i < 4; i++) {
//                Triple triple = new ImmutableTriple(dataFactory.getStreetName(),10, dataFactory.getLastName());
//                cityStreets.add(triple);
//            }
//            existingAddresses.putIfAbsent(city,cityStreets);
//        }

        ProductionStorage packagesStorage = new ProductionStorage();
        List<PackageInfo> packages = Utils.createPackageInfos();
        DeliveryTask deliveryTask = () -> packages;
        DeliveryService deliveryService = new DeliveryService(packagesStorage, deliveryTask);
        FrameMap frameMap = new FrameMap(deliveryService, 4, 10);
        frameMap.drawMap();
        //Act
        deliveryService.startWorkDay();
    }

}
