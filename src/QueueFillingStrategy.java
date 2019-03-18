import java.util.List;
import java.util.Optional;

public interface QueueFillingStrategy {
	public Optional<Integer> selectQueue(Component c, List<InputQueue> inputs);;
}
