import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public enum Workstation
{
	w1(Product.p1),
	w2(Product.p2),
	w3(Product.p3);
	
	private HashMap<Component, Queue<Component>> componentQueues;
	
	private Workstation(Product product)
	{
		List<Component> requiredComponents = product.getRequiredComponents();
		this.componentQueues = new HashMap<Component, Queue<Component>>();
		
		for (Component component : requiredComponents)
		{
			componentQueues.put(component, new LinkedList<Component>());
		}
	}
}
