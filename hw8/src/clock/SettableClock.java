package clock;

import java.time.Instant;
import java.time.temporal.TemporalAmount;

public class SettableClock implements Clock {

    Instant now;

    public SettableClock(Instant now) {
        this.now = now;
    }

    @Override
    public Instant now() {
        return now;
    }

    public Instant getNow() {
        return now;
    }

    public void setNow(Instant now) {
        this.now = now;
    }

    public void plus(TemporalAmount interval) {
        now = now.plus(interval);
    }

}
