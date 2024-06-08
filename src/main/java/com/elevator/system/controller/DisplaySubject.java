package com.elevator.system.controller;

import com.elevator.system.display.DisplayObserver;

public interface DisplaySubject {
    void attach(DisplayObserver observer);
    void detach(DisplayObserver observer);

    void notifyObservers();
}
