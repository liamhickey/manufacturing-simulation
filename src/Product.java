import java.util.List;
import java.util.Arrays;

public enum Product
{
	p1(Arrays.asList(Component.c1)),
	p2(Arrays.asList(Component.c1, Component.c2)),
	p3(Arrays.asList(Component.c1, Component.c3));
	
	private List<Component> requiredComponents;

	private Product(List<Component> requiredComponents)
	{
		this.requiredComponents = requiredComponents;
	}
	
	public List<Component> getRequiredComponents()
	{
		return requiredComponents;
	}
}
