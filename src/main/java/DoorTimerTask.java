

import java.util.TimerTask;

class DoorTimerTask extends TimerTask {
	private IDoorTimeout doorTimeout;

	@Override
	public void run() {
		doorTimeout.doorTimeout() ;
	}

	public void setDoorTimeout(IDoorTimeout doorTimeout) {
		this.doorTimeout = doorTimeout;
	}	
}
