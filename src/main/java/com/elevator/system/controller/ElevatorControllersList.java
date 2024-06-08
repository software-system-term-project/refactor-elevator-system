package com.elevator.system.controller;

import com.elevator.system.util.Floor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ElevatorControllersList {
    private List<ElevatorController> elevatorControllers = new ArrayList<>();

    public int getSize() {
        return elevatorControllers.size();
    }


    public void addController(ElevatorController elevatorController) {
        elevatorControllers.add(elevatorController);
    }

    public ElevatorController getController(int index) {
        if (index < 0 || index >= elevatorControllers.size()) throw new IndexOutOfBoundsException("유효하지 않은 컨트롤러 접근입니다.");
        return elevatorControllers.get(index);
    }

    public boolean isEmpty() {
        return elevatorControllers.isEmpty();
    }

    public void callEmergencyStop(Consumer<ElevatorController> emergencyStopAction) {
        for (ElevatorController elevatorController: elevatorControllers) {
            emergencyStopAction.accept(elevatorController);
        }
    }

    public void moveElevator(int selectedElevator, Floor destination) {
        elevatorControllers.get(selectedElevator).goTo(destination) ;
    }

    public void print() {
        for (ElevatorController controller: elevatorControllers) {
            print(controller);
        }
    }

    public boolean isToBeVisitedFloor(Floor floor) {
        for ( ElevatorController controller: elevatorControllers) {
            if ( !controller.isNewDestination(floor)) return true;
        }
        return false;
    }

    private void print(ElevatorController controller) {
        System.out.println(controller.getCurrentFloor());
        System.out.println(controller.getCurrentDirection());
        System.out.println(controller.getFloorsToBeVisited());
    }


}
