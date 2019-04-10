import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RandomQueueFillingStrategy implements QueueFillingStrategy {

	private Random random;

	public RandomQueueFillingStrategy() {
		random = new Random();
	}

	@Override
	public Optional<InputQueue> selectQueue(Component c, List<InputQueue> inputs) {
		return inputs.stream().filter(inputQueue -> inputQueue.takes(c) && inputQueue.getStock(c) < 2).findAny();
	}
}
