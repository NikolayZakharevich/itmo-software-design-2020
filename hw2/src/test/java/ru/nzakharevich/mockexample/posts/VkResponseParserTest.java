package ru.nzakharevich.mockexample.posts;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author nzakharevich
 */
public class VkResponseParserTest {
    private final static String testResponse =
            "{\n" +
                    "  \"response\": {\n" +
                    "    \"items\": [\n" +
                    "      {\n" +
                    "        \"id\": 41972,\n" +
                    "        \"date\": 1603739584,\n" +
                    "        \"owner_id\": 359245951,\n" +
                    "        \"from_id\": 359245951,\n" +
                    "        \"post_type\": \"post\",\n" +
                    "        \"text\": \"Привет, как тaы?)\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"id\": 2759,\n" +
                    "        \"date\": 1603739583,\n" +
                    "        \"owner_id\": 31217052,\n" +
                    "        \"from_id\": 620806556,\n" +
                    "        \"post_id\": 2758,\n" +
                    "        \"parents_stack\": [],\n" +
                    "        \"post_type\": \"reply\",\n" +
                    "        \"text\": \"Привет)\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"id\": 376,\n" +
                    "        \"date\": 1603739583,\n" +
                    "        \"owner_id\": 374237692,\n" +
                    "        \"from_id\": 620746502,\n" +
                    "        \"post_id\": 374,\n" +
                    "        \"parents_stack\": [],\n" +
                    "        \"post_type\": \"reply\",\n" +
                    "        \"text\": \"Привет)\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"id\": 2047301,\n" +
                    "        \"date\": 1603739583,\n" +
                    "        \"owner_id\": -128776180,\n" +
                    "        \"from_id\": 587332271,\n" +
                    "        \"post_id\": 2046853,\n" +
                    "        \"parents_stack\": [],\n" +
                    "        \"post_type\": \"reply\",\n" +
                    "        \"text\": \"Всем привет, это новый ивент\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"total_count\": 54661\n" +
                    "  }\n" +
                    "}\n";

    @Test
    public void parseResponse() throws Exception {
        VkResponseParser parser = new VkResponseParser();
        PostsInfo info = parser.parseResponse(testResponse);

        Assert.assertTrue(info.totalCount == 54661);

        List<Post> posts = Arrays.asList(
                new Post(41972, 1603739584, 359245951, 359245951, "post", "Привет, как тaы?)"),
                new Post(2759, 1603739583, 31217052, 620806556, "reply", "Привет)"),
                new Post(376, 1603739583, 374237692, 620746502, "reply", "Привет)"),
                new Post(2047301, 1603739583, -128776180, 587332271, "reply", "Всем привет, это новый ивент")
        );

        for (int i = 0; i < posts.size(); i++) {
            Assert.assertEquals(info.postsPage.get(i), posts.get(i));
        }
    }
}
