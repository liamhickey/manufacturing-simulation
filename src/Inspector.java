import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;

public class Inspector extends Thread{

    List<InputQueue> inputs;
    List<Component> components;

    public Inspector(List<InputQueue> inputs, List<Component> components) {
        this.inputs = inputs;
        this.components = components;
    }

    private Optional<Integer> smallestQueue(Component c) {
        return inputs.stream()
                .filter(inputQueue -> inputQueue.takes(c))
                .map(inputQueue -> inputQueue.getStock(c))
                .min(Integer::compareTo);
    }

    public void addComponent() {
        int index = ThreadLocalRandom.current().nextInt(0, components.size());
        Component c = components.get(index);

        int minimum = smallestQueue(c).get();
        Optional<InputQueue> queue = inputs.stream()
                .filter(inputQueue -> inputQueue.takes(c) && inputQueue.getStock(c) < 2)
                .filter(inputQueue -> inputQueue.getStock(c) == minimum)
                .findFirst();

        queue.ifPresent(q -> {
            q.putComponent(c);
            System.out.println(System.currentTimeMillis() + ": " + c + " added to " + q.getName(c));
        });
    }

    public void run() {
        while(true) {
            addComponent();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
