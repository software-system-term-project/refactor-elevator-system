package com.elevator.system.door;

import com.elevator.system.controller.IDoorTimeout;

import java.util.Timer;

public class DoorTimer {
	private Timer timer;
	private DoorTimerTask task;
	
	public DoorTimer() {
		task = new DoorTimerTask() ;
		timer = new Timer() ;
	}
	public void setDoorTimeout(IDoorTimeout doorTimeout) {
		task.setDoorTimeout(doorTimeout);
	}
	public void start() {
		timer.schedule(task, 1000) ;
	}
	public void stop() {
		timer.cancel() ;
	}
}