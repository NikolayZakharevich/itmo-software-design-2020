package ru.nzakharevich.mockexample.posts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author nzakharevich
 */
public class PostManagerTest {

    private PostManager postManager;

    @Mock
    private VkClient client;

    @Before
    public void setUp() throws Exception {
        client = mock(VkClient.class);
        postManager = new PostManager(client);
    }

    @Test
    public void getCompanyNamesWithGrowingPrice() {
        String hashtag = "Привет";
        int nHours = 2;
        when(client.getInfo(anyString(), anyInt(), anyInt())).thenReturn(createAnswer());

        List<Long> frequency = postManager.getPostFrequency(hashtag, nHours);
        Assert.assertEquals(Arrays.asList(10L, 10L), frequency);
    }

    private PostsInfo createAnswer() {
        return new PostsInfo(Arrays.asList(
                new Post(41972, 1603739584, 359245951, 359245951, "post", "Привет, как ты?)"),
                new Post(41972, 1603739584, 359245951, 359245951, "post", "Привет, как ты?)")
        ), 10);
    }

}
