package ru.nzakharevich.mockexample.posts;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nzakharevich
 */
public class VkResponseParser {
    public PostsInfo parseResponse(String response) {
        JsonObject res = new JsonParser().parse(response).getAsJsonObject().getAsJsonObject("response");
        JsonArray items = (JsonArray) res.get("items");
        List<Post> posts = new ArrayList<>(items.size());
        for (JsonElement e : items) {
            JsonObject d = (JsonObject) e;
            posts.add(new Post(
                    d.get("id").getAsLong(),
                    d.get("date").getAsLong(),
                    d.get("owner_id").getAsLong(),
                    d.get("from_id").getAsLong(),
                    d.get("post_type").getAsString(),
                    d.get("text").getAsString()));
        }
        long totalCount = res.get("total_count").getAsLong();
        return new PostsInfo(posts, totalCount);
    }
}
