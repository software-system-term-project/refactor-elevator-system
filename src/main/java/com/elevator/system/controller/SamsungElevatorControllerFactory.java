package com.elevator.system.controller;

import com.elevator.system.door.FloorDoorList;
import com.elevator.system.util.Floor;
import com.elevator.system.door.DoorTimer;
import com.elevator.system.door.elevator.ElevatorDoor;
import com.elevator.system.door.elevator.ElevatorDoorSamsung;
import com.elevator.system.door.floor.FloorDoor;
import com.elevator.system.door.floor.FloorDoorSamsung;
import com.elevator.system.motor.ElevatorMotor;
import com.elevator.system.motor.ElevatorMotorSamsung;

import java.util.ArrayList;
import java.util.List;

//구현 필요
public class SamsungElevatorControllerFactory extends AbstractElevatorControllerFactory {
    private static SamsungElevatorControllerFactory instance;

    private SamsungElevatorControllerFactory() {}

    public static SamsungElevatorControllerFactory getInstance() {
        if (instance == null) instance = new SamsungElevatorControllerFactory();
        return instance;
    }

    @Override
    ElevatorMotor createElevatorMotor() {
        return new ElevatorMotorSamsung();
    }

    @Override
    ElevatorDoor createElevatorDoor() {
        return new ElevatorDoorSamsung();
    }

    @Override
    FloorDoorList createFloorDoors(int floorCount) {
        FloorDoorList floorDoors = new FloorDoorList();
        
        for(int i=0;i<floorCount;i++)
        {
            floorDoors.add(new FloorDoorSamsung(new Floor(i)));
        }

        return floorDoors;
    }

    @Override
    DoorTimer createDoorTimer() {
        return new DoorTimer();
    }

    @Override
    ElevatorControllerKind setElevatorControllerKind() {
        return ElevatorControllerKind.EveryFloorStop;
    }
}
