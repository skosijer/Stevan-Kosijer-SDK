package io.github.skosijer.lotr.client;

import static io.github.skosijer.lotr.util.ApiConstants.BEARER;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpClient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthHttpClient {

    private static HttpClient instance;

    // Single instance to provide better performance as generally advised
    public static synchronized HttpClient getInstance() {
        if (instance == null) {
            instance = HttpClient.newHttpClient();
        }
        return instance;
    }

    public static synchronized void initialize(String bearerToken) {
        if (instance == null) {
            instance = HttpClient.newBuilder()
                .authenticator(createBasicAuthenticator(bearerToken))
                .build();
        }
    }

    private static Authenticator createBasicAuthenticator(String bearerToken) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(BEARER, bearerToken.toCharArray());
            }
        };
    }
}
