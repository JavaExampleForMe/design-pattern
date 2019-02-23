package postman;

import java.util.function.BiConsumer;

public interface Observable {
    void addObserver(Observer observer, BiConsumer<String,Address> consumer);
    void removeObserver(Observer observer);
    void notifyObserver(String fromAddress, Address toAddress);

}
