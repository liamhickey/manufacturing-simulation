
public class ComponentWrapper {
	private static long objectId = 0;

	private Component component;
	private long startTime;
	private long endTime;
	private long Id;
	
	public ComponentWrapper(Component component, long startTime) {
		this.component = component;
		this.startTime = startTime;
		this.Id = objectId++;
	}
	
	public Component getComponent() {
		return component;
	}
	
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public long getSystemTime() {
		return endTime - startTime;
	}

	public long getId() {
		return Id;
	}
}
