package postman.decorator;

import lombok.SneakyThrows;
import postman.logic.*;
import postman.ui.City;
import postman.ui.DestinationPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ParallelPostman implements Postman {

    private Postman postman;
    private ExecutorService executor;

    public ParallelPostman(Postman postman) {
       // executor = Executors.newWorkStealingPool(3);//newFixedThreadPool(City.values().length);
        executor = Executors.newFixedThreadPool(3);//newFixedThreadPool(City.values().length);
        this.postman = postman;
    }

    @Override
    @SneakyThrows
    public int deliver(DeliveryTask deliveryTask) {
        List<DeliveryTask> bulks = toBulks(deliveryTask);
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (DeliveryTask bulkDeliveryTask : bulks) {
            Callable<Integer> task = () -> postman.deliver(bulkDeliveryTask);
            tasks.add(task);
        }
        List<Future<Integer>> futures = executor.invokeAll(tasks);
        int totalTime = 0;
        for (Future<Integer> task : futures) {
            final int currDuration = task.get();
            totalTime = Math.max(currDuration, totalTime);
        }
        return totalTime;
    }


    private List<DeliveryTask> toBulks(DeliveryTask deliveryTask) {
        List<DeliveryTask> result = new ArrayList<>();
      City[] cities = deliveryTask.getAllPackages().stream()
              .map(PackageInfo::getAddress)
              .map(Address::getCity)
              .distinct()
              .sorted((i1, i2) -> ((Integer)i1.ordinal()).compareTo(i2.ordinal()))
              .toArray(City[]::new);
        for (City currCity : cities) {
            DeliveryTask deliveryPerCity = () -> deliveryTask.getAllPackages().stream()
                    .filter(packageInfo -> packageInfo.getAddress().getCity()==currCity)
                    .collect(Collectors.toList());
               result.add(deliveryPerCity);
        }
        return result;
    }

    @Override
    public void addObserver(Observer observer, Consumer<DestinationPackage> consumer) {
        postman.addObserver(observer, consumer);
    }

    @Override
    public void removeObserver(Observer observer) {
        postman.removeObserver(observer);
    }

    @Override
    public void notifyObserver(DestinationPackage destinationPackage) {
        postman.notifyObserver(destinationPackage);
    }

}
