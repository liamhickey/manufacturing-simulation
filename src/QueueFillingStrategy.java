import java.util.List;
import java.util.Optional;

public interface QueueFillingStrategy {
	public Optional<InputQueue> selectQueue(Component c, List<InputQueue> inputs);;
}
