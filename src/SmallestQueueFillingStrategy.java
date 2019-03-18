import java.util.List;
import java.util.Optional;

public class SmallestQueueFillingStrategy implements QueueFillingStrategy {

	@Override
	public Optional<Integer> selectQueue(Component c, List<InputQueue> inputs) {
		return inputs.stream()
                .filter(inputQueue -> inputQueue.takes(c))
                .map(inputQueue -> inputQueue.getStock(c))
                .min(Integer::compareTo);
	}
}
