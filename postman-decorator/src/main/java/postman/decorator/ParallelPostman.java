package postman.decorator;

import lombok.SneakyThrows;
import postman.*;
import postman.UI.City;

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
            DeliveryTask deliveryPerCity = new DeliveryTask() {
                    @Override
                    public List<PackageInfo> getAllPackages() {
                        return deliveryTask.getAllPackages().stream().filter(packageInfo -> packageInfo.getAddress().getCity()==currCity).collect(Collectors.toList());
                    }
                };
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
////    public int deliver1(DeliveryTask deliveryTask) {
////        return IntStream.range(0,numOfThreads)
////                .parallel()
////                .map(i->{
////                    try {
////                        return deliveries.deliver(deliveryTask);
////                    } catch (ExecutionException e) {
////                        e.printStackTrace();
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                    // if the task failed to start, then return a value that will not effect the max.
////                    return -1;
////                })
////                .filter(result->result>0)
////                .max()
////                // you better return OptionalInt and not call the following function
////                // because it maybe not your job to worry about what happen when there
////                // are no successful tasks. leave the logic of "nothing to return" to the user.
////                // ** if you know what to do then ignore this comment.
////                .getAsInt();
////    }
}
