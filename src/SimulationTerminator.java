
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
            		"\n C1 L=" + (simulation.sumOfC1SystemTimes / simulation.totalNumC1) + " l=" + ((double) simulation.totalNumC1 / (double) simTime) + " W=" + simulation.runningNumC1 +
            		"\n C2 L=" + (simulation.sumOfC2SystemTimes / simulation.totalNumC2) + " l=" + ((double) simulation.totalNumC2 / (double) simTime) + " W=" + simulation.runningNumC2 +
            		"\n C3 L=" + (simulation.sumOfC3SystemTimes / simulation.totalNumC3) + " l=" + ((double) simulation.totalNumC3 / (double) simTime) + " W=" + simulation.runningNumC3
            		); 
            System.exit(0);
        }
	}
}
