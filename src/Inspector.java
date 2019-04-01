import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Inspector extends Thread {
	Simulation simulation;
    List<InputQueue> inputs;
    List<Component> components;
    private QueueFillingStrategy queueFillingStrategy;
    private ComponentWrapper lastComponent;
    private HashMap<Component, Double> lambdas;
    private Random random;

    public Inspector(Simulation simulation, List<InputQueue> inputs, List<Component> components, QueueFillingStrategy queueFillingStrategy, HashMap<Component, Double> lambdas) {
        random = new Random();
        this.lambdas = lambdas;
        this.simulation = simulation;
    	this.inputs = inputs;
        this.components = components;
        this.queueFillingStrategy = queueFillingStrategy;
    }

    public void addComponent() {
        int index = ThreadLocalRandom.current().nextInt(0, components.size());
        lastComponent = new ComponentWrapper(components.get(index), System.currentTimeMillis());
        simulation.addComponent(lastComponent);

        queueFillingStrategy.selectQueue(lastComponent.getComponent(), inputs).ifPresent(q -> {
            q.putComponent(lastComponent);
            System.out.println(System.currentTimeMillis() + ": " + lastComponent.getComponent() + "<" + lastComponent.getId() + ">" + " added to " + q.getName(lastComponent.getComponent()));
        });
    }
    
    public double getServiceTime() {
		return Math.log(1 - random.nextDouble()) / (-lambdas.get(lastComponent.getComponent()));
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
