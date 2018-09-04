package com.example;

import java.util.function.Consumer;

public class User implements Observer {

    private Observable observable = null;

    public User(Observable observable) {


        Consumer<Color> action = new Consumer<Color>() {
            @Override
            public void accept(Color color) {
                if (color == Color.RED){
                    buyDress();
                    unsubscribe();
                } else
                    System.out.println("Dress is not RED");
            }
        };
        this.observable = observable;
        observable.addObserver(this, action);
    }


    private void unsubscribe() {
        System.out.println("Unsubscribing");
        observable.removeObserver(this);
    }

    private void buyDress() {
        System.out.println("Got my new dress");
    }

}
