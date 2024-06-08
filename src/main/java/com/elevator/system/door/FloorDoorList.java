package com.elevator.system.door;

import com.elevator.system.door.floor.FloorDoor;
import com.elevator.system.util.Floor;

import java.util.ArrayList;
import java.util.List;

public class FloorDoorList {
    private final List<FloorDoor> floorDoors = new ArrayList<>();

    public void add(FloorDoor floorDoor) {
        floorDoors.add(floorDoor);
    }

    public FloorDoor get(int index) {
        if (index < 0 || index >= floorDoors.size()) throw new IndexOutOfBoundsException("잘못된 접근입니다.");
        return floorDoors.get(index);
    }

    public FloorDoor get(Floor floor) {
        int floorIndex = floor.getValue() - 1;
        if (floorIndex < 0 || floorIndex >= floorDoors.size()) throw new IndexOutOfBoundsException("잘못된 접근입니다.");
        return floorDoors.get(floorIndex);
    }
}
