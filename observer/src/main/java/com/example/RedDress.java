package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RedDress implements com.example.Observable {

    private Map<Observer, Consumer<Color>> users = new HashMap<>();
    private boolean inStock = false;
    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock, Color color){
        if (inStock) {
            notifyObserver(color);
            this.inStock = inStock;
        }
    }

    public void addObserver(Observer observer, Consumer consumer) {
       users.putIfAbsent(observer, consumer);
    }

    public void removeObserver(Observer observer) {
        users.remove(observer);
    }

    public void notifyObserver(Color color) {
        for (Consumer<Color> consumer : users.values()) {
            consumer.accept(color);
        }
    }
}
