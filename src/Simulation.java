public class Simulation {
	public static final double WS1_LAMBDA = 0.217183;
	public static final double WS2_LAMBDA = 0.09015;
	public static final double WS3_LAMBDA = 0.113693;
	
	public static final double SERVINSP11_LAMBDA = 0.0965446;
	public static final double SERVINSP22_LAMBDA = 0.06436289;
	public static final double SERVINSP23_LAMBDA = 0.048466621;
	
	public static void main(String[] args) {
		ComponentQueue b1 = new ComponentQueue(Component.c1, "w1");
		CompoundComponentQueue b2 = new CompoundComponentQueue(Component.c1, Component.c2, "w2");
		CompoundComponentQueue b3 = new CompoundComponentQueue(Component.c1, Component.c3, "w3");
		QueueFillingStrategy strategy = new SmallestQueueWs1HighestFillingStrategy();

		Inspector insp1 = new InspectorBuilder()
				.addComponent(Component.c1)
				.addInput(b1)
				.addInput(b2)
				.addInput(b3)
				.addLambda(Component.c1, SERVINSP11_LAMBDA)
				.addFillingStrategy(strategy)
				.build();

		Inspector insp2 = new InspectorBuilder()
				.addComponent(Component.c2)
				.addComponent(Component.c3)
				.addInput(b2)
				.addInput(b3)
				.addLambda(Component.c2, SERVINSP22_LAMBDA)
				.addLambda(Component.c3, SERVINSP23_LAMBDA)
				.addFillingStrategy(strategy)
				.build();

		Workstation w1 = new Workstation(Product.p1, b1, WS1_LAMBDA);
		Workstation w2 = new Workstation(Product.p2, b2, WS2_LAMBDA);
		Workstation w3 = new Workstation(Product.p3, b3, WS3_LAMBDA);

		insp1.start();
		insp2.start();

		w1.start();
		w2.start();
		w3.start();
	}
}
