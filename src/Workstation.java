import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Workstation extends Thread
{
	private List<Component> requiredComponents;
	private InputQueue queue;
	private Product product;
	private double lambda;
	private Random random;
	
	public Workstation(Product product, InputQueue queue, double lambda)
	{
		random = new Random();
		requiredComponents = product.getRequiredComponents();
		this.lambda = lambda;
		this.queue = queue;
		this.product = product;
	}

	public Optional<Product> makeProduct() {
		List<ComponentWrapper> usedComponents = queue.getFirst();
		Stream<ComponentWrapper> components = queue.getComponents();
		long remaining = components.filter(component -> !requiredComponents.contains(component.getComponent())).count();
		if(remaining == 0) {
			usedComponents.forEach(component -> component.setEndTime(System.currentTimeMillis()));
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
