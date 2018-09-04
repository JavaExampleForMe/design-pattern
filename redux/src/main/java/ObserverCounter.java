import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;


public class ObserverCounter {

    private OurState lastState;
    private List<Consumer<OurState>> consumers;
    public ObserverCounter() {
        this.lastState = new OurState(0);
        this.consumers = new LinkedList<>();
    }

    public static void main(String[] args) {
        ObserverCounter store = new ObserverCounter();
        store.subscribe((state) -> System.out.println("subscriber1: new state: " + state));
        store.subscribe((state) -> System.out.println("subscriber2: new state: " + state));

        store.dispach(new OurState(4));
        store.dispach(new OurState(-97));
    }

    public synchronized void subscribe(Consumer<OurState> consumer) {
        this.consumers.add(consumer);
        consumer.accept(this.lastState);
    }

    public synchronized void unsubscribe(Consumer<OurState> consumer) {
        this.consumers.remove(consumer);
    }


    public synchronized void dispach(OurState newState) {
        if (!newState.equals(this.lastState)) {
            this.lastState = newState;
            this.consumers.forEach(consumer -> consumer.accept(newState));
        }
    }
}
