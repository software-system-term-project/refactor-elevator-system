package com.elevator.system.controller;

import com.elevator.system.util.Floor;
import com.elevator.system.door.DoorTimer;
import com.elevator.system.door.elevator.ElevatorDoor;
import com.elevator.system.door.elevator.ElevatorDoorHyundai;
import com.elevator.system.door.floor.FloorDoor;
import com.elevator.system.door.floor.FloorDoorHyundai;
import com.elevator.system.motor.ElevatorMotor;
import com.elevator.system.motor.ElevatorMotorHyundai;

import java.util.ArrayList;
import java.util.List;

public class HyundaiElevatorControllerFactory extends AbstractElevatorControllerFactory {
    private static HyundaiElevatorControllerFactory instance;

    private HyundaiElevatorControllerFactory() {}

    public static HyundaiElevatorControllerFactory getInstance() {
        if (instance == null) instance = new HyundaiElevatorControllerFactory();
        return instance;

    }

    @Override
    ElevatorMotor createElevatorMotor() {
        return new ElevatorMotorHyundai();
    }

    @Override
    ElevatorDoor createElevatorDoor() {
        return new ElevatorDoorHyundai();
    }

    @Override
    List<FloorDoor> createFloorDoors(int floorCount) {
        List<FloorDoor> floorDoors = new ArrayList<FloorDoor>();
        
        for(int i=0;i<floorCount;i++)
        {
            floorDoors.add(new FloorDoorHyundai(new Floor(i)));
        }
        
        return floorDoors;
    }

    @Override
    DoorTimer createDoorTimer() {
        return new DoorTimer();
    }

    @Override
    ElevatorControllerKind setElevatorControllerKind() {
        return ElevatorControllerKind.DemandOnly;
    }
}