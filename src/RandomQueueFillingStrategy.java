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
		return Optional.of(inputs.get(random.nextInt(inputs.size())));
	}
}
