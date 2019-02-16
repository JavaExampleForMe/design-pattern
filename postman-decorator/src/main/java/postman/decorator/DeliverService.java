package postman.decorator;

import postman.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DeliverService {
    private int numberStreets;
    private int numHousesInStreet;
    private Delivery delivery;
    private FrameMap frameMap;

    public DeliverService(int numberStreets, int numHousesInStreet) {
        this.numberStreets = numberStreets;
        this.numHousesInStreet = numHousesInStreet;
    }
    public HashMap<Address, JButton> drawMap(){
        frameMap = new FrameMap(numberStreets,numHousesInStreet);
        HashMap<Address, JButton> existingAddresses = frameMap.drawMap();
        delivery = new DeliveryImpl(frameMap); // 4273
        delivery = new SortDelivery(delivery); // 3027
        delivery = new ParallelDelivery(delivery, 4); // 1105
        return existingAddresses;
    }

    public int deliver(DeliveryTask deliveryTask) {
        JLabel clockLabel = frameMap.getClockLabel();
        Timer timer = new Timer(1, new ActionListener() {
            int clockCounter = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                clockLabel.setText(String.valueOf(clockCounter++));
            }

        });
        timer.start();
        int deliver = delivery.deliver(deliveryTask);
        timer.stop();
        return deliver;
    }
}
