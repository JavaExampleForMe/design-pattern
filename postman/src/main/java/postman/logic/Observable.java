package postman.logic;

import postman.ui.DestinationPackage;

import java.util.function.Consumer;

public interface Observable {
    void addObserver(Observer observer, Consumer<DestinationPackage> consumer);
    void removeObserver(Observer observer);
    void notifyObserver(DestinationPackage destinationPackage);

}
