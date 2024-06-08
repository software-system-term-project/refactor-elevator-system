package com.elevator.system.display.implementor;

import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;

public interface IFloorDisplayImplementor {
	void activateDisplay();
	void deactivateDisplay();
	
	void showCurrentPosition(Floor floor);
	void expressCurrentPositionByVoice(Floor floor);
	void showDirection(Direction direction);
	void expressDirectionByVoice(Direction direction);
}
