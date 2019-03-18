import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SmallestQueueWs3HighestFillingStrategy implements QueueFillingStrategy {

	@Override
	public Optional<InputQueue> selectQueue(Component c, List<InputQueue> inputs) {
		int minimum = inputs.stream()
                .filter(inputQueue -> inputQueue.takes(c))
                .map(inputQueue -> inputQueue.getStock(c))
                .min(Integer::compareTo).get();
        return inputs.stream()
                .filter(inputQueue -> inputQueue.takes(c) && inputQueue.getStock(c) < 2)
                .filter(inputQueue -> inputQueue.getStock(c) == minimum)
                .reduce((first, second) -> second);
	}

}
