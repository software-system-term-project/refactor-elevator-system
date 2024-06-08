public class FloorDoorControllerLG extends FloorDoorController {

    private int doorOpenedLG;

    public FloorDoorControllerLG(DeviceVendor vendor, Floor floor)
    {
        super(vendor, floor);
        doorOpenedLG = 0;
    }

    public void open() {
        super.open();
        doorOpenedLG = 1;
    }
    public void close() {
        super.close();
        doorOpenedLG = 0;
    }

    public DoorStatus getDoorStatus() {
        DoorStatus status = null;
        status = (doorOpenedLG == 1) ? DoorStatus.OPEN : DoorStatus.CLOSED;
        return status;
    }
}
