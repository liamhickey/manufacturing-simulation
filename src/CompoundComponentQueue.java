import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CompoundComponentQueue implements InputQueue {

    private HashMap<Component, ComponentQueue> queues;

    public CompoundComponentQueue(Component c1, Component c2, String name) {
        queues = new HashMap<>();
        queues.put(c1, new ComponentQueue(c1, name));
        queues.put(c2, new ComponentQueue(c2, name));
    }

    public synchronized void putComponent(ComponentWrapper c) {
        queues.get(c.getComponent()).putComponent(c);
//        System.out.println("+ " + queues.get(c).getName(null) + " has " + queues.get(c).getStock() + " of " + queues.get(c).getComponent());
        notify();
    }

    public boolean containsBoth() {
        for(ComponentQueue queue : queues.values()) {
//            System.out.println("? " + queue.getName(null) + " has " + queue.getStock() + " of " + queue.getComponent());
            if(queue.getStock() == 0){
                return false;
            }
        }
        return true;
    }

    public synchronized Stream<ComponentWrapper> getComponents() {
        while(!containsBoth()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notify();
        return queues.values().stream().flatMap(ComponentQueue::getComponents);
    }

    public String getName(Component c) {
        return queues.get(c).getName(c);
    }

    public int getStock(Component c) {
        return queues.get(c).getStock(c);
    }

    public boolean takes(Component c) {
        return queues.containsKey(c);
    }

	@Override
	public List<ComponentWrapper> getFirst() {
		List<ComponentWrapper> firstComponents = new ArrayList<>();
		// firstComponents will have either 1 or 0 elements here
		queues.values().stream().forEach(componentQueue -> firstComponents.addAll(componentQueue.getFirst()));
		return firstComponents;
	}
}
