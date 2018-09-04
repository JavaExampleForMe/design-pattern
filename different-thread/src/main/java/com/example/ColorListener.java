package com.example;

import java.util.function.Consumer;

public interface ColorListener {

    void addObserver(ColorListening colorListening, Consumer consumer);
    void removeObserver(ColorListening colorListening);
    void notifyObserver(Color color);
}
