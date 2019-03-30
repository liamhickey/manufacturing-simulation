
public class ComponentWrapper {
	private Component component;
	
	private long startTime;
	private long endTime;
	
	public ComponentWrapper(Component component, long startTime) {
		this.component = component;
		this.startTime = startTime;
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
}
