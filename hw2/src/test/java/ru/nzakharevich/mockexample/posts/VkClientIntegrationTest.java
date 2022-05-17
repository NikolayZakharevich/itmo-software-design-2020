package ru.nzakharevich.mockexample.posts;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import ru.nzakharevich.mockexample.rule.HostReachableRule;

import java.util.Arrays;
import java.util.List;

/**
 * @author nzakharevich
 */
@HostReachableRule.HostReachable(VkClientIntegrationTest.HOST)
public class VkClientIntegrationTest {
    public static final String HOST = "api.vk.com";

    @ClassRule
    public static final HostReachableRule rule = new HostReachableRule();

    @Test
    public void getInfo() {
        VkClient client = new VkClient(HOST);
        PostsInfo infos = client.getInfo("Привет", 1603739584, 1603739584);
        Assert.assertNotNull(infos);
    }
}

