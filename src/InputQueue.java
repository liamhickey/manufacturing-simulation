import java.util.List;
import java.util.stream.Stream;

public interface InputQueue {
    public void putComponent(ComponentWrapper c);
    public Stream<ComponentWrapper> getComponents();
    public boolean takes(Component c);
    public int getStock(Component c);
    public String getName(Component c);
	public List<ComponentWrapper> getFirst();
}
