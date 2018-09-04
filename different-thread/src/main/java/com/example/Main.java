package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        RedDress redDress = new RedDress();
        User user = new User(redDress);

        final CompletableFuture<Void> executeDeletion = CompletableFuture.runAsync(() -> {
            user.buyDress();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redDress.setInStock(true, Color.BLUE);
        redDress.setInStock(true, Color.RED);

        try {
            executeDeletion.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
