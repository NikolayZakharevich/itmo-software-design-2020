package ru.nzakharevich.mockexample.posts;

import ru.nzakharevich.mockexample.http.UrlReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nzakharevich
 */
public class VkClient {
    private final String ACCESS_TOKEN = "";
    private final String API_VERSION = "5.124";

    private final String host;
    private final VkResponseParser parser;
    private final UrlReader reader;

    public VkClient(String host) {
        this.host = host;
        this.parser = new VkResponseParser();
        this.reader = new UrlReader();
    }

    public PostsInfo getInfo(String q, int startTime, int endTime) {
        Map<String, String> params = new HashMap<>(2);
        params.put("q", q);
        params.put("start_time", String.valueOf(startTime));
        params.put("end_time", String.valueOf(endTime));

        String url = createUrl("newsfeed.search", params);
        String response = reader.readAsText(url);
        return parser.parseResponse(response);
    }

    private String createUrl(String method, Map<String, String> params) {
        params.put("v", API_VERSION);
        params.put("access_token", ACCESS_TOKEN);
        return "https://" + host + "/method/" + method + "?" + convertToQueryString(params);
    }

    private static String convertToQueryString(Map<String, String> params) {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(((Map.Entry<?, ?>) stringStringEntry).getKey())
                    .append("=")
                    .append(((Map.Entry<?, ?>) stringStringEntry).getValue());
        }
        return sb.toString();
    }
}
