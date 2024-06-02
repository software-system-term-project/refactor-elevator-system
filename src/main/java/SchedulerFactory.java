import java.util.Calendar;

public class SchedulerFactory {
    private static SchedulerFactory instance;
    private ElevatorScheduler scheduler;

    private SchedulerFactory() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour < 12) {
            scheduler = new ResponseTimeScheduler();
        } else {
            scheduler = new ThroughputScheduler();
        }
    }

    public static SchedulerFactory getInstance() {
        if (instance == null) {
            instance = new SchedulerFactory();
        }
        return instance;
    }

    public ElevatorScheduler createScheduler() {
        return scheduler;
    }
}
