import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Workstation extends Thread
{
	Simulation simulation;
	private List<Component> requiredComponents;
	private InputQueue queue;
	private Product product;
	private double lambda;
	private Random random;
	
	public Workstation(Simulation simulation, Product product, InputQueue queue, double lambda)
	{
		this.simulation = simulation;
		random = new Random();
		requiredComponents = product.getRequiredComponents();
		this.lambda = lambda;
		this.queue = queue;
		this.product = product;
	}

	public Optional<Product> makeProduct() {
		List<ComponentWrapper> components = queue.getComponents().collect(Collectors.toList());
		List<Component> usedComponents = components.stream().map(ComponentWrapper::getComponent).collect(Collectors.toList());
		if(usedComponents.containsAll(requiredComponents)) {
			components.forEach(component -> simulation.addComponentSystemTime(System.currentTimeMillis() - component.getStartTime()));
			return Optional.of(product);
		}
		return Optional.empty();
	}
	
	public double getServiceTime() {
		return Math.log(1 - random.nextDouble()) / (-lambda);
	}

	@Override
	public void run() {
		while(true) {
			makeProduct().ifPresent(product -> {
				System.out.println(System.currentTimeMillis() + ": " + product + " produced.");
			});
			try {
				Thread.sleep((long) getServiceTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
