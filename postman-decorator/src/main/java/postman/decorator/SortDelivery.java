package postman.decorator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortDelivery implements Delivery {

    private Delivery delivery;

    public SortDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public int deliver(DeliveryTask deliveryTask) {
        DeliveryTask newDeliveryTask = new DeliveryTask() {
            @Override
            public int[] getCitiesIds() {
                return deliveryTask.getCitiesIds();
            }

            @Override
            public List<PackageInfo> getAllPackages() {
                Comparator<PackageInfo> comp = new Comparator<PackageInfo>() {
                    @Override
                    public int compare(PackageInfo obj1, PackageInfo obj2) {
                        if (obj1 == obj2) {
                            return 0;
                        }
                        if (obj1 == null) {
                            return -1;
                        }
                        if (obj2 == null) {
                            return 1;
                        }
                        if (obj1.cityId==obj2.cityId) {
                            if  (obj1.street.equals(obj2.street))
                                return obj1.number-obj2.number;
                             else
                                return obj1.street.compareTo(obj2.street);
                        }
                        return obj1.cityId-obj2.cityId;
                    }
                } ;

                return deliveryTask.getAllPackages().stream().sorted(comp).collect(Collectors.toList());
            }

            @Override
            public String[] getStreet(int cityId) {
                return deliveryTask.getStreet(cityId);
            }
        };
        return delivery.deliver(newDeliveryTask);

    }
}
