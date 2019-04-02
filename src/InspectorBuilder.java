import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InspectorBuilder {
	private Simulation simulation;
	private List<InputQueue> inputs = new ArrayList<>();
	private List<Component> components = new ArrayList<>();
	private HashMap<Component, Double> lambdas = new HashMap<Component, Double>();
	private QueueFillingStrategy queueFillingStrategy;

	public InspectorBuilder addInput(InputQueue i) {
		inputs.add(i);
		return this;
	}

	public InspectorBuilder addComponent(Component c) {
		components.add(c);
		return this;
	}
	
	public InspectorBuilder addLambda(Component c, double lambda) {
		lambdas.put(c, lambda);
		return this;
	}

	public InspectorBuilder addFillingStrategy(QueueFillingStrategy strategy) {
		queueFillingStrategy = strategy;
		return this;
	}
	
	public InspectorBuilder addSimulation(Simulation simulation) {
		this.simulation = simulation;
		return this;
	}

	public Inspector build() {
		return new Inspector(simulation, inputs, components, queueFillingStrategy, lambdas);
	}
}
