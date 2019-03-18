import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Workstation extends Thread
{
	private List<Component> requiredComponents;
	private InputQueue queue;
	private Product product;
	
	public Workstation(Product product, InputQueue queue)
	{
		requiredComponents = product.getRequiredComponents();
		this.queue = queue;
		this.product = product;
	}

	public Optional<Product> makeProduct() {
		Stream<Component> components = queue.getComponents();
		long remaining = components.filter(component -> !requiredComponents.contains(component)).count();
		if(remaining == 0) return Optional.of(product);
		return Optional.empty();
	}

	@Override
	public void run() {
		while(true) {
			makeProduct().ifPresent(product -> {
				System.out.println(System.currentTimeMillis() + ": " + product + " produced.");
			});
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
