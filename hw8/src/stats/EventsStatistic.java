package stats;

import java.util.Map;

public interface EventsStatistic {

    void incEvent(String name);

    double getEventStatisticByName(String name);

    Map<String, Double> getAllEventStatistic();

    void printStatistic();
}

//incEvent(String name) - инкрементит число событий name;
//getEventStatisticByName(String name) - выдает rpm (request per minute)
//события name за последний час;
//getAllEventStatistic() - выдает rpm всех произошедших событий за
//прошедший час;
//printStatistic() - выводит в консоль rpm всех произошедших событий;