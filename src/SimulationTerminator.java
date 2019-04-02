
public class SimulationTerminator extends Thread {
	
	Simulation simulation;
	private long terminationCount;
	private final long startTime;
	
	public SimulationTerminator(long terminationCount, Simulation simulation) {
		startTime = System.currentTimeMillis();
		this.terminationCount = terminationCount;
		this.simulation = simulation;
	}
	
	public void run() {
    	synchronized (simulation) {
            while (simulation.getTotalNumComponents() < terminationCount) {
                try {
					simulation.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

            long simTime = (System.currentTimeMillis() - startTime);
            System.out.println(System.currentTimeMillis() + ": Simulation reached cap of " + terminationCount +
            		"\nComponent Average Time in System: " + simulation.getComponentSystemTimeAverage() +
            		"\nSimulation Time in Miliseconds:   " + simTime + 
            		"\nEffective Arrival Rate of System: " + ((double) simulation.getTotalNumComponents() / (double) simTime) +
            		"\nAverage Num Components in System: " + simulation.getRunningNumComponents() +
            		"\n total number of components: " + simulation.getTotalNumComponents());
            System.exit(0);
        }
	}
}
