
import clock.SettableClock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stats.EventsStatistic;
import stats.EventsStatisticImpl;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;


public class EventsStatisticTest {
  private final static long HOUR = 60;

  private SettableClock clock;
  private EventsStatistic eventsStatistic;

  @Before
  public void setUp() {
    clock = new SettableClock(Instant.now());
    eventsStatistic = new EventsStatisticImpl(clock);
  }

  @Test
  public void unknownEvent() {
    assertEquals(0, eventsStatistic.getEventStatisticByName("Kek"));
  }

  @Test
  public void oneHourOneEvent() {
    int nTimes = 10;
    incEventN("Hello", nTimes);
    assertEquals((double) nTimes / HOUR, eventsStatistic.getEventStatisticByName("Hello"));
  }

  @Test
  public void oneHourManyEvents() {
    eventsStatistic.incEvent("Event1");
    eventsStatistic.incEvent("Event2");
    eventsStatistic.incEvent("Event3");
    Assert.assertEquals(1. / 60, eventsStatistic.getEventStatisticByName("Event1"), 1e-10);
    Assert.assertEquals(1. / 60, eventsStatistic.getEventStatisticByName("Event2"), 1e-10);
    Assert.assertEquals(1. / 60, eventsStatistic.getEventStatisticByName("Event3"), 1e-10);
    Map<String, Double> statistic = eventsStatistic.getAllEventStatistic();
    Assert.assertEquals(statistic.size(), 3);
  }

  @Test
  public void deleteAfterHour() {
    eventsStatistic.incEvent("Hello");
    eventsStatistic.incEvent("Hello");
    eventsStatistic.incEvent("Hello");
    Assert.assertEquals(3. / 60, eventsStatistic.getEventStatisticByName("Hello"), 1e-10);

    clock.setNow(clock.getNow().plus(60, ChronoUnit.MINUTES));

    Assert.assertEquals(0. / 60, eventsStatistic.getEventStatisticByName("Hello"), 1e-10);
  }

  @Test
  public void MultiHour() {
    String name = "Hi";
    incEventN(name, 2);
    assertEquals(2. / 60, eventsStatistic.getEventStatisticByName(name));

    clock.plus(Duration.ofMinutes(30));
    eventsStatistic.incEvent(name);
    assertEquals(3. / 60, eventsStatistic.getEventStatisticByName(name));

    clock.plus(Duration.ofMinutes(50));
    assertEquals(1. / 60, eventsStatistic.getEventStatisticByName(name));

    clock.plus(Duration.ofMinutes(30));
    assertEquals(0, eventsStatistic.getEventStatisticByName(name));

  }

  private void incEventN(String name, int nTimes) {
    for (int i = 0; i < nTimes; i++) {
      eventsStatistic.incEvent(name);
    }

  }

  private static void assertEquals(double expected, double actual) {
    Assert.assertEquals(expected, actual, 1e-10);
  }
}