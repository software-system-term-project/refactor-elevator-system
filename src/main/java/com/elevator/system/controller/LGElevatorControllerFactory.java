package com.elevator.system.controller;

import com.elevator.system.util.Floor;
import com.elevator.system.door.DoorTimer;
import com.elevator.system.door.elevator.ElevatorDoor;
import com.elevator.system.door.elevator.ElevatorDoorLG;
import com.elevator.system.door.floor.FloorDoor;
import com.elevator.system.door.floor.FloorDoorLG;
import com.elevator.system.motor.ElevatorMotor;
import com.elevator.system.motor.ElevatorMotorLG;

import java.util.ArrayList;
import java.util.List;

public class LGElevatorControllerFactory extends AbstractElevatorControllerFactory {
    private static LGElevatorControllerFactory instance;

    private LGElevatorControllerFactory() {}

    public static LGElevatorControllerFactory getInstance() {
        if (instance == null) instance = new LGElevatorControllerFactory();
        return instance;
    }

    @Override
    ElevatorMotor createElevatorMotor() {
        return new ElevatorMotorLG();
    }

    @Override
    ElevatorDoor createElevatorDoor() {
        return new ElevatorDoorLG();
    }

    @Override
    List<FloorDoor> createFloorDoors(int floorCount) {
        List<FloorDoor> floorDoors = new ArrayList<FloorDoor>();
        
        for(int i=0;i<floorCount;i++)
        {
            floorDoors.add(new FloorDoorLG(new Floor(i)));
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
