package com.elevator.system.controller;

import com.elevator.system.display.DisplayObserver;

import java.util.ArrayList;
import java.util.List;

public class DisplayManager implements DisplaySubject {
    private List<DisplayObserver> displayObservers = new ArrayList<>();

    @Override
    public void attach(DisplayObserver observer) {
        displayObservers.add(observer);
    }

    @Override
    public void detach(DisplayObserver observer) {
        displayObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (DisplayObserver observer : displayObservers) {
            observer.update();
        }
    }
}
