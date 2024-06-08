package com.elevator.system.door.floor;

import com.elevator.system.util.Floor;

public abstract class FloorDoor {
	private Floor floor;


	public FloorDoor(Floor floor) {
		this.floor = floor;
	}

	public void open() {
		System.out.println(floor + "th com.elevator.system.util.Floor Door Open");
	}
	public void close() {
		System.out.println(floor + "th com.elevator.system.util.Floor Door Close");
	}

	public Floor getFloor() { return floor; }

	abstract public boolean isOpened();
}