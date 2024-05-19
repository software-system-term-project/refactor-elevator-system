
public interface IFloorDisplayImplementor {
	void activateDisplay();
	void deactivateDisplay();
	
	void showCurrentPosition(Floor floor);
	void expressCurrentPositionByVoice(Floor floor);
	void showDirection(int direction);
	void expressDirectionByVoice(int direction);
}
