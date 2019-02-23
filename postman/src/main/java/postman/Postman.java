package postman;

public interface Postman extends Observable{
    int deliver(DeliveryTask deliveryTask);
}
