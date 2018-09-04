package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RedDress implements ColorListener {

    private Map<ColorListening, Consumer<Color>> users = new HashMap<>();
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

    public void addObserver(ColorListening colorListening, Consumer consumer) {
       users.putIfAbsent(colorListening, consumer);
    }

    public void removeObserver(ColorListening colorListening) {
        users.remove(colorListening);
    }

    public void notifyObserver(Color color) {
        for (Consumer<Color> consumer : users.values()) {
            consumer.accept(color);
        }
    }
}
