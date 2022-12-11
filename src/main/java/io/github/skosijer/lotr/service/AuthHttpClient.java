package io.github.skosijer.lotr.service;

import static io.github.skosijer.lotr.util.ApiConstants.BEARER;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpClient;

public class AuthHttpClient {

    private static HttpClient instance;

    private AuthHttpClient() {
        // Private constructor to prevent multiple instances
    }

    public static synchronized void initialize(String bearerToken) {
        if (instance == null) {
            instance = HttpClient.newBuilder()
                .authenticator(createBasicAuthenticator(bearerToken))
                .build();
        }
    }

    public static synchronized HttpClient getInstance() {
        if (instance == null) {
            instance = HttpClient.newHttpClient();
        }
        return instance;
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
