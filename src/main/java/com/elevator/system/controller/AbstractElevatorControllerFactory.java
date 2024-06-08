package com.elevator.system.controller;

import com.elevator.system.door.DoorController;
import com.elevator.system.door.DoorTimer;
import com.elevator.system.door.FloorDoorList;
import com.elevator.system.door.elevator.ElevatorDoor;
import com.elevator.system.door.floor.FloorDoor;
import com.elevator.system.motor.ElevatorMotor;

import java.util.List;

public abstract class AbstractElevatorControllerFactory {
    abstract ElevatorMotor createElevatorMotor();
    abstract ElevatorDoor createElevatorDoor();
    abstract FloorDoorList createFloorDoors(int floorCount);

    abstract DoorTimer createDoorTimer();

    abstract ElevatorControllerKind setElevatorControllerKind();

    public ElevatorController createElevatorController(int floorCount) {
        return new ElevatorController(
                setElevatorControllerKind(),
                createElevatorMotor(),
                new DoorController(
                        createElevatorDoor(),
                        createFloorDoors(floorCount),
                        createDoorTimer()
                        )
        );
    }
}
