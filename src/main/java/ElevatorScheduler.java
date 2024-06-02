import java.util.List;

public interface ElevatorScheduler {
    int selectElevator(List<ElevatorController> controllers, Floor destination, Direction direction);
}
