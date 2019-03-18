public class Simulation {
	public static void main(String[] args) {
		ComponentQueue b1 = new ComponentQueue(Component.c1, "w1");
		CompoundComponentQueue b2 = new CompoundComponentQueue(Component.c1, Component.c2, "w2");
		CompoundComponentQueue b3 = new CompoundComponentQueue(Component.c1, Component.c3, "w3");

		Inspector insp1 = new InspectorBuilder()
				.addComponent(Component.c1)
				.addInput(b1)
				.addInput(b2)
				.addInput(b3)
				.build();

		Inspector insp2 = new InspectorBuilder()
				.addComponent(Component.c2)
				.addComponent(Component.c3)
				.addInput(b2)
				.addInput(b3)
				.build();

		Workstation w1 = new Workstation(Product.p1, b1);
		Workstation w2 = new Workstation(Product.p2, b2);
		Workstation w3 = new Workstation(Product.p3, b3);

		insp1.start();
		insp2.start();

		w1.start();
		w2.start();
		w3.start();
	}
}
