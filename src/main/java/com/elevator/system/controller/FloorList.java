package com.elevator.system.controller;

import com.elevator.system.util.Floor;

import java.util.ArrayList;
import java.util.List;

public class FloorList {
    List<Floor> floors = new ArrayList<>();

    public boolean contains(Floor destination) {
        return floors.contains(destination);
    }

    public void add(Floor destination) {
        floors.add(destination);
    }

    public boolean isEmpty() {
        return floors.isEmpty();
    }

    public boolean remove(Floor floor) {
        return floors.remove(floor);
    }

    public Floor get(int index) {
        if (index < 0 || index >= floors.size()) throw new IndexOutOfBoundsException("잘못된 접근입니다.");
        return floors.get(index);
    }

}
