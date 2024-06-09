package com.elevator.system.motor;

import com.elevator.system.door.DoorController;
import com.elevator.system.util.Direction;
import com.elevator.system.util.Floor;
import com.elevator.system.motor.util.MotorStatus;
import com.elevator.system.controller.ElevatorController;

public abstract class ElevatorMotor {
	private DoorController doorController ;
	private MotorStatus motorStatus = MotorStatus.STOPPED;

	public void setElevatorController(DoorController elevatorController) {
		if (this.doorController != null) return;
		if (elevatorController == null) throw new IllegalArgumentException("elevatorController가 null이 될 수 없습니다.");

		this.doorController = elevatorController ;
	}
	public MotorStatus getMotorStatus() {
		return motorStatus;
	}
	private void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus = motorStatus;
	}

	public void move(Floor currentFloor, Direction direction) {
		if (  getMotorStatus() == MotorStatus.MOVING) return ;

		if ( doorController.isOpenedAt(currentFloor) ) return;
		setMotorStatus(MotorStatus.MOVING) ;
	}

	public void stop() {
		setMotorStatus(MotorStatus.STOPPED);
	}
}
