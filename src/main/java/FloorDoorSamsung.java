public class FloorDoorSamsung extends FloorDoor {

    private char doorOpenedSamsung;

    public FloorDoorSamsung(Floor floor)
    {
        super(floor);
        doorOpenedSamsung = 'X';
    }

    public void open() {
        super.open();
        doorOpenedSamsung = 'O';
    }
    public void close() {
        super.close();
        doorOpenedSamsung = 'X';
    }

    @Override
    public boolean isOpened() {
        return doorOpenedSamsung == 'O';
    }
}