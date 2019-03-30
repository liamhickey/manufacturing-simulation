import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Inspector extends Thread {

    List<InputQueue> inputs;
    List<Component> components;
    private QueueFillingStrategy queueFillingStrategy;
    private Component lastComponent;
    private HashMap<Component, Double> lambdas;
    private Random random;

    public Inspector(List<InputQueue> inputs, List<Component> components, QueueFillingStrategy queueFillingStrategy, HashMap<Component, Double> lambdas) {
        random = new Random();
        this.lambdas = lambdas;
    	this.inputs = inputs;
        this.components = components;
        this.queueFillingStrategy = queueFillingStrategy;
    }

    public void addComponent() {
        int index = ThreadLocalRandom.current().nextInt(0, components.size());
        lastComponent = components.get(index);

        queueFillingStrategy.selectQueue(lastComponent, inputs).ifPresent(q -> {
            q.putComponent(lastComponent);
            System.out.println(System.currentTimeMillis() + ": " + lastComponent + " added to " + q.getName(lastComponent));
        });
    }
    
    public double getServiceTime() {
		return Math.log(1 - random.nextDouble()) / (-lambdas.get(lastComponent));
	}

    public void run() {
        while(true) {
            addComponent();
            try {
                Thread.sleep((long) getServiceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
