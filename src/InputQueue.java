import java.util.stream.Stream;

public interface InputQueue {
    public void putComponent(Component c);
    public Stream<Component> getComponents();
    public boolean takes(Component c);
    public int getStock(Component c);
    public String getName(Component c);
}
