import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Inspector extends Thread{

    List<InputQueue> inputs;
    List<Component> components;
    private QueueFillingStrategy queueFillingStrategy;

    public Inspector(List<InputQueue> inputs, List<Component> components, QueueFillingStrategy queueFillingStrategy) {
        this.inputs = inputs;
        this.components = components;
        this.queueFillingStrategy = queueFillingStrategy;
    }

    public void addComponent() {
        int index = ThreadLocalRandom.current().nextInt(0, components.size());
        Component c = components.get(index);

        int minimum = queueFillingStrategy.selectQueue(c, inputs).get();
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
