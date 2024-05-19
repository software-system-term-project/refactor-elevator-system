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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (isValidFloor(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Invalid floor value. Floor must be between -100 and 1000.");
        }
    }

    private boolean isValidFloor(int value) {
        return value >= MIN_FLOOR && value <= MAX_FLOOR;
    }

    @Override
    public String toString() {
        return value + "층";
    }
}
