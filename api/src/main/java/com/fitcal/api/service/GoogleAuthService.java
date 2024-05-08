package com.fitcal.api.service;

import com.fitcal.api.enums.GoogleAuthMessages;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleAuthService {

    private static final String CLIENT_ID = "1075462838223-6r7k8rfofknaqert59tft0n2doirm1m1.apps.googleusercontent.com";
    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new JacksonFactory();

    /**
     * Authenticate with Google using an ID token.
     * @param idTokenString The ID token provided by Google.
     * @return A GoogleAuthMessages object indicating the result 
     * of the authentication.
     */
    public GoogleAuthMessages authenticateWithGoogle(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                // Successful authentication
                return GoogleAuthMessages.SUCCESS;
            } else {
                // Invalid token
                return GoogleAuthMessages.INVALID;
            }
        } catch (Exception e) {
            // Error in token verification
            return GoogleAuthMessages.ERROR;
        }
    }
}
