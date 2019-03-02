package postman.logic;

import postman.ui.DestinationPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class DeliveryService{

    protected final PackagesStorage packagesStorage;

    public void setPostman(Postman postman) {
        this.postman = postman;
    }

    private Postman postman;
    protected DeliveryTask deliveryTask;

    public DeliveryService(PackagesStorage packagesStorage, DeliveryTask deliveryTask) {
        this.packagesStorage = packagesStorage;
        postman = new PostmanImpl(this.packagesStorage);
        this.deliveryTask = deliveryTask;
    }

    public void startWorkDay(){
        packagesStorage.addPackages(deliveryTask.getAllPackages());
        int secondsCounter = 0;
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //clockLabel.setText(String.valueOf(secondsCounter++));
            }
        });
        timer.start();
        postman.deliver(deliveryTask);
        timer.stop();
    }

    public void registerForPostmanRoute(Observer observer, Consumer<DestinationPackage> consumer) {
        postman.addObserver(observer, consumer);
    }

}
