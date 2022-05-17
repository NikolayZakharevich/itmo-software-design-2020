package ru.nzakharevich.mockexample.posts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nzakharevich
 */
public class PostManager {
    private final VkClient client;

    public PostManager(VkClient client) {
        this.client = client;
    }

    private final int HOUR = 3600;

    public List<Long> getPostFrequency(String hashtag, int nHours) {
        assert (1 <= nHours && nHours <= 24) : "Invalid number of hours passed";

        List<List<Integer>> dateBounds = new ArrayList<>();
        int now = (int) (System.currentTimeMillis() / 1000);
        for (int time = now - nHours * HOUR; time < now; time += HOUR) {
            dateBounds.add(Arrays.asList(time, time + HOUR));
        }
        return dateBounds.stream()
                .mapToLong(bounds -> client.getInfo(hashtag, bounds.get(0), bounds.get(1)).totalCount)
                .boxed()
                .collect(Collectors.toList());
    }
}
