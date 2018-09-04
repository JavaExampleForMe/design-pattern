import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;


enum OurActions {
    INCREMENT,
    DECREMENT,
    INITIAL
}

public class ReduxCounter {
    public static void main(String[] args) {
        OurStore store = new OurStore();
        store.subscribe((state, action) -> System.out.println("subscriber1: new state: " + state + ", action: " + action));
        store.subscribe((state, action) -> System.out.println("subscriber2: new state: " + state + ", action: " + action));

        store.dispach(OurActions.INCREMENT);
        store.dispach(OurActions.DECREMENT);
    }
}

class OurState {
    private int counter;

    OurState(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OurState)) return false;
        OurState ourState = (OurState) o;
        return counter == ourState.counter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter);
    }

    @Override
    public String toString() {
        return "OurState{" +
                "counter=" + counter +
                '}';
    }
}

class Reducer {
    public static OurState reduce(OurState lastState, OurActions newAction) {
        switch (newAction) {
            case DECREMENT:
                return new OurState(lastState.getCounter() - 1);
            case INCREMENT:
                return new OurState(lastState.getCounter() + 1);
        }
        return lastState;
    }
}

class OurStore {
    private OurState lastState;
    private OurActions lastAction;
    private List<BiConsumer<OurState, OurActions>> consumers;

    public OurStore() {
        this.lastState = new OurState(0);
        this.lastAction = OurActions.INITIAL;
        this.consumers = new LinkedList<>();
    }

    public synchronized void subscribe(BiConsumer<OurState, OurActions> consumer) {
        this.consumers.add(consumer);
        consumer.accept(this.lastState, this.lastAction);
    }

    public synchronized void unsubscribe(BiConsumer<OurState, OurActions> consumer) {
        this.consumers.remove(consumer);
    }


    public synchronized void dispach(OurActions action) {
        OurState newState = Reducer.reduce(this.lastState, action);
        if (!newState.equals(this.lastState)) {
            this.lastState = newState;
            this.lastAction = action;
            this.consumers.forEach(consumer -> consumer.accept(newState, action));
        }
    }

}