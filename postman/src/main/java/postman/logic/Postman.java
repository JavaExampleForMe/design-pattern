package postman.logic;

public interface Postman extends Observable{
    int deliver(DeliveryTask deliveryTask);
}
