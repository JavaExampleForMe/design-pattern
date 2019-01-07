package postman.decorator;

import java.util.concurrent.ExecutionException;

public interface Delivery {
    int deliver(DeliveryTask deliveryTask);
}
