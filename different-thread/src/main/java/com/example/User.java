package com.example;

import java.util.function.Consumer;

public class User implements ColorListening {

    private ColorListener colorListener = null;

    public User(ColorListener colorListener) {


        Consumer<Color> action = new Consumer<Color>() {
            @Override
            public void accept(Color color) {
                if (color == Color.RED){
                    synchronized (colorListener) {
                        String name = Thread.currentThread().getName();
                        System.out.println(name+" notifying on " + colorListener);
                        colorListener.notify();
                    }

               } else
                    System.out.println("Dress is " + color);
            }
        };
        this.colorListener = colorListener;
        colorListener.addObserver(this, action);
    }

    public void  buyDress() {
        String name = Thread.currentThread().getName();
        System.out.println(name+" wating on " + colorListener);
        synchronized (colorListener) {
            try {
                System.out.println(name+" waiting to get notified at time:"+System.currentTimeMillis());
                colorListener.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+" waiter thread got notified at time:"+System.currentTimeMillis());
        }
        System.out.println("Got my new dress");
        unsubscribe();
    }

    private void unsubscribe() {
        System.out.println("Unsubscribing");
        colorListener.removeObserver(this);
    }

 }
