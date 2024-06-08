package com.elevator.system.controller;

public class DisplayManagerFactory {
    private static DisplayManagerFactory displayManagerFactory;

    private DisplayManagerFactory() {}

    private static DisplayManagerFactory getInstance() {
        if (displayManagerFactory == null) displayManagerFactory = new DisplayManagerFactory();
        return displayManagerFactory;
    }
}
