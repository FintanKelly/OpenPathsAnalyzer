package openpaths;

import java.sql.Time;

/**
 *
 * @author Fintan Kelly
 */
public class Statistic {
    private float distance;
    private int days;
    private Time time;
    private String device;
    
    public Statistic(float newDistance, int newAmountDays, Time newTime, String newDevice) {
        distance = newDistance;
        days = newAmountDays;
        time = newTime;
        device = newDevice;
    }
    
    
}
