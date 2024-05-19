
public class SamsungFloorDisplayImplementor implements IFloorDisplayImplementor {
	private boolean activated = true;
	public void activateDisplay() {
		activated = true;
	}
	public void deactivateDisplay() {
		activated = false;
	}
	public void showCurrentPosition(int floor) {
		if ( ! activated ) return;
		System.out.println("Samsung Display: Current Position " + floor);
	}
	public void expressCurrentPositionByVoice(int floor) {
		if ( ! activated ) return;
		System.out.println("Samsung Voice: Current Position " + floor);
	}
	public void showDirection(int direction) {
		if ( ! activated ) return;
		System.out.println("Samsung Display: Current Direction " + direction);
	}
	public void expressDirectionByVoice(int direction) {
		if ( ! activated ) return;
		System.out.println("Samsung Voice: Current Direction " + direction);
	}
}
