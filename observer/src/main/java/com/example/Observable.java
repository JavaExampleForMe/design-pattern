package com.example;

import java.util.function.Consumer;

public interface Observable {

    void addObserver(com.example.Observer observer, Consumer consumer);
    void removeObserver(com.example.Observer observer);
    void notifyObserver(Color color);
}
