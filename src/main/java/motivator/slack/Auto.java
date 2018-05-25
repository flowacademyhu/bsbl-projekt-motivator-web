package motivator.slack;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class Auto {

    public void automatedSlackMessage(int hour, int minute, int second){

        Timer timer = new Timer();
        Calendar startingTime = Calendar.getInstance(TimeZone.getDefault());
        startingTime.set(Calendar.HOUR_OF_DAY, hour);
        startingTime.set(Calendar.MINUTE, minute);
        startingTime.set(Calendar.SECOND, second);

        timer.schedule(new TimerTask() {
            public void run() {

            }

        }, startingTime.getTime(), 1000 * 60 * 60 * 24);
    }
}
