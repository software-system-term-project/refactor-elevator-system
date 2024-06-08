package com.elevator.system.door.elevator;

import com.elevator.system.door.DoorStatus;

public class ElevatorDoorHyundai extends ElevatorDoor {
	@Override
	public void open() 
	{
		if (isOpened()) return;
		System.out.println("OPEN Hyundai Elevator Door") ;
		elevatorDoorStatus = DoorStatus.OPEN;
	}

	@Override
	public void close() 
	{
		if (!isOpened()) return ;
		System.out.println("CLOSE Hyundai Elevator Door") ;
		elevatorDoorStatus = DoorStatus.CLOSED;
	}
}