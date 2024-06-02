
public class ElevatorDoorFactory {
	private static ElevatorDoorFactory instance;

    private ElevatorDoorFactory() {}

    public static ElevatorDoorFactory getInstance() {
        if (instance == null) instance = new ElevatorDoorFactory();
        return instance;
    }

	public ElevatorDoor GetElevatorDoor()
	{
		return new ElevatorDoor();
	}
}