public class Floor {
    private int value;
    private static final int MIN_FLOOR = -100;
    private static final int MAX_FLOOR = 1000;

    public Floor(int value) {
        if (isValidFloor(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Invalid floor value. Floor must be between -100 and 1000.");
        }
    }

    private boolean isValidFloor(int value) {
        if (value >= MIN_FLOOR && value <= MAX_FLOOR) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return value + "층";
    }
}
