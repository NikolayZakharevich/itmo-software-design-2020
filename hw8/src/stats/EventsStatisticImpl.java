package stats;

import clock.Clock;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


public class EventsStatisticImpl implements EventsStatistic {

    private HashMap<String, List<Instant>> events = new HashMap<>();

    private final Clock clock;

    private final static long HOUR = 60;

    public EventsStatisticImpl(Clock clock) {
        this.clock = clock;
    }

    public void incEvent(String name) {
        if (!events.containsKey(name)) {
            events.put(name, new ArrayList<>());
        }
        events.get(name).add(clock.now());
    }

    public double getEventStatisticByName(String name) {
        update();
        return getStatisticByName(name);
    }

    public Map<String, Double> getAllEventStatistic() {
        update();
        return events.keySet().stream().collect(Collectors.toMap(x -> x, this::getStatisticByName));
    }

    public void printStatistic() {
        getAllEventStatistic().forEach((key, value) -> System.out.println(key + " " + value));
    }

    private void update() {
        Instant hourAgo = clock.now().minus(Duration.ofHours(1));
        events.replaceAll((n, v) -> events.get(n).stream()
                .filter(instant -> instant.isAfter(hourAgo))
                .collect(Collectors.toList()));
    }

    private double getStatisticByName(String name) {
        if (events.containsKey(name)) {
            return (double) events.get(name).size() / HOUR;
        }
        return 0;
    }

}
