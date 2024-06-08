package com.elevator.system.door.elevator;

import com.elevator.system.door.DoorStatus;

public class ElevatorDoorLG extends ElevatorDoor {
	@Override
	public void open() 
	{
		if ( elevatorDoorStatus == DoorStatus.OPEN ) return ;
		System.out.println("OPEN LG Elevator Door") ;
		elevatorDoorStatus = DoorStatus.OPEN;
	}

	@Override
	public void close() 
	{
		if ( elevatorDoorStatus == DoorStatus.CLOSED ) return ;
		System.out.println("CLOSE LG Elevator Door") ;
		elevatorDoorStatus = DoorStatus.CLOSED;
	}
}