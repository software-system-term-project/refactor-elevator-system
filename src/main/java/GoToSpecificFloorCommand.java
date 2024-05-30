public class GoToSpecificFloorCommand implements Command {
    private ElevatorController elevatorController;

    private Floor floor;

    public GoToSpecificFloorCommand(ElevatorController elevatorController, Floor floor) {
        this.elevatorController = elevatorController;
        this.floor = floor;
    }

    @Override
    public void execute() {
        elevatorController.goTo(floor);
    }
}
