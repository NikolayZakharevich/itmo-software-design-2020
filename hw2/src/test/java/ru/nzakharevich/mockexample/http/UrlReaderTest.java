package ru.nzakharevich.mockexample.http;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import ru.nzakharevich.mockexample.rule.HostReachableRule;

/**
 * @author nzakharevich
 */
@HostReachableRule.HostReachable("api.vk.com")
public class UrlReaderTest {

    @ClassRule
    public static final HostReachableRule rule = new HostReachableRule();

    @Test
    public void read() {
        String result = new UrlReader()
                .readAsText("http://api.vk.com/");
        Assert.assertTrue(result.length() > 0);
    }
}
