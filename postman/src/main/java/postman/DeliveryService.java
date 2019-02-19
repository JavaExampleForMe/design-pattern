package postman;

public class DeliveryService {


    private Postman postman;
    private DeliveryTasksManager deliveryTasksManager;

    public DeliveryService(DeliveryTasksManager deliveryTasksManager) {
        this.deliveryTasksManager = deliveryTasksManager;
        postman = new PostmanImpl(this.deliveryTasksManager);
    }

    public void startWorkDay(){

        postman.deliver();

    }

}
