package postman.decorator;

import lombok.SneakyThrows;
import postman.logic.*;
import postman.ui.City;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ParallelPostman implements Postman {

    private Postman postman;
    private ExecutorService executor;

    public ParallelPostman(Postman postman) {
        executor = Executors.newFixedThreadPool(City.values().length);
        this.postman = postman;
    }

    @Override
    @SneakyThrows
    public int deliver(DeliveryTask deliveryTask) {
        List<DeliveryTask> bulks = toBulks(deliveryTask);
        List<Future<Integer>> tasks = new ArrayList<>();
        for (DeliveryTask bulkDeliveryTask : bulks) {
            Callable<Integer> task = () -> postman.deliver(bulkDeliveryTask);
            tasks.add(executor.submit(task));
        }

        int totalTime = 0;
        for (Future<Integer> task : tasks) {
            final int currDuration = task.get();
            totalTime = Math.max(currDuration, totalTime);
        }
        return totalTime;
    }


    private List<DeliveryTask> toBulks(DeliveryTask deliveryTask) {
        List<DeliveryTask> result = new ArrayList<>();
        City[] cities = deliveryTask.getAllPackages().stream().map(PackageInfo::getAddress).map(Address::getCity).distinct().toArray(City[]::new);
        for (City currCity : cities) {
            DeliveryTask deliveryPerCity = () -> deliveryTask.getAllPackages().stream().filter(packageInfo -> packageInfo.getAddress().getCity()==currCity).collect(Collectors.toList());
            result.add(deliveryPerCity);
        }
        return result;
    }

    @Override
    public void addObserver(Observer observer, BiConsumer<String,Address> consumer) {
        postman.addObserver(observer, consumer);
    }

    @Override
    public void removeObserver(Observer observer) {
        postman.removeObserver(observer);
    }

    @Override
    public void notifyObserver(String addressee, Address toAddress) {
        postman.notifyObserver(addressee, toAddress);
    }

}
