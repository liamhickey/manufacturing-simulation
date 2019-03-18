import java.util.LinkedList;
import java.util.stream.Stream;

public class ComponentQueue implements InputQueue {

	private static final int MAX_SIZE = 2;
    private LinkedList<Component> queue;
    private Component c;
    private String name;

    public ComponentQueue(Component c, String name) {
        queue = new LinkedList<>();
        this.c = c;
        this.name = name;
    }

    public synchronized void putComponent(Component c) {
        while(queue.size() >= MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        notifyAll();

        queue.add(c);
    }

    public synchronized Stream<Component> getComponents() {
        while(queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        notifyAll();

        return Stream.of(queue.remove());
    }

    public String getName(Component c){
        return name;
    }

    public Component getComponent() {
        return c;
    }

    public int getStock(Component c) {
        return getStock();
    }

    public int getStock() {
        return queue.size();
    }

    public boolean takes(Component c) {
        return this.c == c;
    }
}
