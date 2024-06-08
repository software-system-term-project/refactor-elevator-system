package com.elevator.system.display;

import com.elevator.system.display.DisplayObserver;

public interface DisplaySubject {
    void attach(DisplayObserver observer);
    void detach(DisplayObserver observer);

    void notifyObservers();
}
