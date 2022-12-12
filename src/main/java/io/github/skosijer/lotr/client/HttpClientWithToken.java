package io.github.skosijer.lotr.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpClientWithToken {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER = "Bearer";

    public static HttpClient httpClient;

    public static String bearerToken;

    public static void initialize(String token) {
        httpClient = HttpClient.newHttpClient();
        bearerToken = token;
    }

    public static HttpRequest createRequest(String uri) {
        return HttpRequest.newBuilder()
            .header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER, HttpClientWithToken.bearerToken))
            .uri(URI.create(uri))
            .GET()
            .build();
    }
}
