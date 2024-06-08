package com.elevator.system;

import com.elevator.system.button.Button;
import com.elevator.system.button.MoveDownCommand;
import com.elevator.system.button.MoveUpCommand;
import com.elevator.system.controller.ElevatorController;
import com.elevator.system.controller.HyundaiElevatorControllerFactory;
import com.elevator.system.controller.SamsungElevatorControllerFactory;
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
		
		List<ElevatorController> elevatorControllers = createElevatorControllers(floorCount);
		
		SimpleElevatorManager simpleElevatorManager = new SimpleElevatorManager(elevatorControllers, scheduler);
		
		List<Button> requestButtons = new ArrayList<>();
		for ( int i = 0; i < floorCount; i ++ ) {
			Floor floor = new Floor(i + 1);

			requestButtons.add(new Button(new MoveDownCommand(simpleElevatorManager, floor)));
			requestButtons.add(new Button(new MoveUpCommand(simpleElevatorManager, floor)));
		}
		requestButtons.get(0).pressed();
	}

	private static List<ElevatorController> createElevatorControllers(int floorCount) {
		List<ElevatorController> elevatorControllers = new ArrayList<>();
		
		// Devices for Elevator 1
		elevatorControllers.add(createFirstElevator(floorCount));

		// Devices for Elevator 2
		elevatorControllers.add(createSecondElevator(floorCount));

		return elevatorControllers;
	}

	private static ElevatorController createSecondElevator(int floorCount) {
		ElevatorController elevatorController = HyundaiElevatorControllerFactory
				.getInstance()
				.createElevatorController(floorCount);

		IFloorDisplayImplementor imp = new SamsungFloorDisplayImplementor();
		elevatorController.attach(new AdvancedFloorDisplay(elevatorController, imp));
		return elevatorController;
	}

	private static ElevatorController createFirstElevator(int floorCount) {
		ElevatorController elevatorController = SamsungElevatorControllerFactory
				.getInstance()
				.createElevatorController(floorCount);

		elevatorController.attach(new ControlRoomDisplay(elevatorController));
		elevatorController.attach(new ElevatorInsideDisplay(elevatorController));

		return elevatorController;
	}
}
