//package postman.decorator;
//
//import postman.City;
//import postman.Delivery;
//import postman.DeliveryTask;
//import postman.PackageInfo;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class SortDelivery implements Delivery {
//
//    private Delivery delivery;
//
//    public SortDelivery(Delivery delivery) {
//        this.delivery = delivery;
//    }
//
//    @Override
//    public int deliver(DeliveryTask deliveryTask) {
//        DeliveryTask newDeliveryTask = new DeliveryTask() {
//            @Override
//            public City[] getCities() {
//                return deliveryTask.getCities();
//            }
//
//            @Override
//            public List<PackageInfo> getAllPackages() {
//                Comparator<PackageInfo> comp = new Comparator<PackageInfo>() {
//                    @Override
//                    public int compare(PackageInfo obj1, PackageInfo obj2) {
//                        return obj1.getAddress().compareTo(obj2.getAddress());
//                    }
//                } ;
//
//                return deliveryTask.getAllPackages().stream().sorted(comp).collect(Collectors.toList());
//            }
//
//            @Override
//            public String[] getStreet(City city) {
//                return deliveryTask.getStreet(city);
//            }
//        };
//        return delivery.deliver(newDeliveryTask);
//
//    }
//}
