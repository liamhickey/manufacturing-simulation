import java.util.ArrayList;
import java.util.List;

public class InspectorBuilder {
    private List<InputQueue> inputs = new ArrayList<>();
    private List<Component> components = new ArrayList<>();

    public InspectorBuilder addInput(InputQueue i) {
        inputs.add(i);
        return this;
    }

    public InspectorBuilder addComponent(Component c) {
        components.add(c);
        return this;
    }

    public Inspector build() {
        return new Inspector(inputs, components);
    }
}
