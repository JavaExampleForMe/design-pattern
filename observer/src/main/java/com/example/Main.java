package com.example;

public class Main {
    public static void main(String[] args) {

        RedDress redDress = new RedDress();
        User user = new User(redDress);

        redDress.setInStock(true, Color.BLUE);
        redDress.setInStock(true, Color.RED);

    }
}
