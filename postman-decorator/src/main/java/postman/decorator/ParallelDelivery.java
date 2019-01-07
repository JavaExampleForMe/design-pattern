package postman.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ParallelDelivery implements Delivery {

    private Delivery delivery;
    private ExecutorService executor;

    public ParallelDelivery(Delivery delivery, int numOfThreads) {
        executor = Executors.newFixedThreadPool(numOfThreads);
        this.delivery = delivery;
    }

    @Override
    public int deliver(DeliveryTask deliveryTask) {
        List<DeliveryTask> bulks = toBulks(deliveryTask);
        List<Future<Integer>> tasks = new ArrayList<>();
        for (DeliveryTask bulkDeliveryTask : bulks) {
            Callable<Integer> task = () -> delivery.deliver(bulkDeliveryTask);
            tasks.add(executor.submit(task));
        }

        int totalTime = 0;
        for (Future<Integer> task : tasks) {
            try {
                final int currDuration = task.get();
                totalTime = Math.max(currDuration, totalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return totalTime;
    }


    private List<DeliveryTask> toBulks(DeliveryTask deliveryTask) {
        List<DeliveryTask> result = new ArrayList<>();

        for (int currCityId : deliveryTask.getCitiesIds()) {
            DeliveryTask deliveryPerCityPerStreet = new DeliveryTask() {
                    @Override
                    public int[] getCitiesIds() {
                        return new int[]{currCityId};
                    }

                    @Override
                    public List<PackageInfo> getAllPackages() {
                        return deliveryTask.getAllPackages().stream().filter(packageInfo -> packageInfo.cityId==currCityId).collect(Collectors.toList());
                    }

                    @Override
                    public String[] getStreet(int cityId) {
                        return getStreet(cityId);
                    }
                };
            result.add(deliveryPerCityPerStreet);
        }
        return result;
    }


//    public int deliver1(DeliveryTask deliveryTask) {
//        return IntStream.range(0,numOfThreads)
//                .parallel()
//                .map(i->{
//                    try {
//                        return deliveries.deliver(deliveryTask);
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    // if the task failed to start, then return a value that will not effect the max.
//                    return -1;
//                })
//                .filter(result->result>0)
//                .max()
//                // you better return OptionalInt and not call the following function
//                // because it maybe not your job to worry about what happen when there
//                // are no successful tasks. leave the logic of "nothing to return" to the user.
//                // ** if you know what to do then ignore this comment.
//                .getAsInt();
//    }


}
