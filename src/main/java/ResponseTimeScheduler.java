import java.util.List;

public class ResponseTimeScheduler implements ElevatorScheduler{
    public int selectElevator(List<ElevatorController> controllers, Floor destination, Direction direction) {
        return controllers.size() - 1;
    }
}
