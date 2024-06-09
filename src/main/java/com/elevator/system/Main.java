package com.elevator.system;

import com.elevator.system.button.ButtonsList;
import com.elevator.system.button.Button;
import com.elevator.system.button.MoveDownCommand;
import com.elevator.system.button.MoveUpCommand;
import com.elevator.system.controller.*;
import com.elevator.system.display.AdvancedFloorDisplay;
import com.elevator.system.display.ControlRoomDisplay;
import com.elevator.system.display.ElevatorInsideDisplay;
import com.elevator.system.display.implementor.IFloorDisplayImplementor;
import com.elevator.system.display.implementor.SamsungFloorDisplayImplementor;
import com.elevator.system.scheduler.ElevatorScheduler;
import com.elevator.system.scheduler.SchedulerFactory;
import com.elevator.system.util.Floor;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		int floorCount = 5;
		ElevatorScheduler scheduler = SchedulerFactory
				.getInstance()
				.createScheduler();
		
		ElevatorControllersList elevatorControllers = createElevatorControllers(floorCount);
		
		SimpleElevatorManager simpleElevatorManager = new SimpleElevatorManager(elevatorControllers, scheduler);
		
		ButtonsList buttons = new ButtonsList();
		for ( int i = 0; i < floorCount; i ++) {
			Floor floor = new Floor(i + 1);

			buttons.addButton(new Button(new MoveDownCommand(simpleElevatorManager, floor)));
			buttons.addButton(new Button(new MoveUpCommand(simpleElevatorManager, floor)));
		}
		buttons.pressButton(0);
	}

	private static ElevatorControllersList createElevatorControllers(int floorCount) {
		ElevatorControllersList elevatorControllers = new ElevatorControllersList();
		
		// Devices for Elevator 1
		elevatorControllers.addController(createFirstElevator(floorCount));

		// Devices for Elevator 2
		elevatorControllers.addController(createSecondElevator(floorCount));

		return elevatorControllers;
	}

	private static ElevatorController createSecondElevator(int floorCount) {
		ElevatorController elevatorController = HyundaiElevatorControllerFactory
				.getInstance()
				.createElevatorController(floorCount);

		IFloorDisplayImplementor imp = new SamsungFloorDisplayImplementor();
		DisplayManager displayManager = new DisplayManager();
		displayManager.attach(new AdvancedFloorDisplay(elevatorController.getElevatorStatus(), imp));
		elevatorController.setDisplayManager(displayManager);
		return elevatorController;
	}

	private static ElevatorController createFirstElevator(int floorCount) {
		ElevatorController elevatorController = SamsungElevatorControllerFactory
				.getInstance()
				.createElevatorController(floorCount);

		DisplayManager displayManager = new DisplayManager();
		displayManager.attach(new ControlRoomDisplay(elevatorController.getElevatorStatus()));
		displayManager.attach(new ElevatorInsideDisplay(elevatorController.getElevatorStatus()));
		elevatorController.setDisplayManager(displayManager);

		return elevatorController;
	}
}
