package com.fitcal.api;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@SpringBootApplication
public class ApiApplication {

	private static final String CLIENT_ID = "426540645158-nrmlsa10pio3pnhnt91tpjhf8jo7p25v.apps.googleusercontent.com";

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	/*
	 * CORS configuration to allow requests from any origin 
	 * and any HTTP method on all routes of the application.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}

	/*
	 * Configuration of Google ID token verification.
	 * Creates a GoogleIdToken verifier using an HttpTransport object 
	 * and a JsonFactory object.
	 * The verifier is configured with the client's audience specified 
	 * by CLIENT_ID.
	 */
	@Bean
	public GoogleIdTokenVerifier googleIdTokenVerifier() {
		// Create HttpTransport object
		HttpTransport transport = new NetHttpTransport();

		// Create JsonFactory object
		JsonFactory jsonFactory = new JacksonFactory();

		// Create and configure the GoogleIdToken verifier
		return new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(CLIENT_ID))
				.build();
	}

	/*
	 * RestTemplate configuration.
	 * Creates a RestTemplate instance that will be used to 
	 * make REST calls in the application.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
