package com.example;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class HttpUtil {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @PostConstruct
    public void init() {
        HttpUtil.localOAuth2RestTemplate = oAuth2RestTemplate;
    }

    private static OAuth2RestTemplate localOAuth2RestTemplate;

    public static boolean isRestSuccess(ResponseEntity<String> userObject) {
        return userObject.getStatusCode() == HttpStatus.OK;
    }

    public static ResponseEntity<String> exchangeRestAPI(String url, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(null, headers);
        return localOAuth2RestTemplate.exchange(url, method, entity, String.class);
    }

    @SuppressWarnings("unchecked")
	public static Map<String, Object> exchangeRestWithBodyToMap(String url, HttpMethod method, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = new Gson().toJson(body);
        HttpEntity<?> entity = new HttpEntity<>(json, headers);
        // noinspection unchecked
        return localOAuth2RestTemplate.exchange(url, method, entity, Map.class).getBody();
    }

    public static String exchangeRestWithBodyToString(String url, HttpMethod method, Map<String, String> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = new Gson().toJson(body);
        HttpEntity<?> entity = new HttpEntity<>(json, headers);
        return localOAuth2RestTemplate.exchange(url, method, entity, String.class).getBody();
    }

    @Async
    public CompletableFuture<?> exchangeAsyncRestWithBodyToString(String url, HttpMethod method, Map<String, String> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = new Gson().toJson(body);
        HttpEntity<?> entity = new HttpEntity<>(json, headers);
        return CompletableFuture.completedFuture(localOAuth2RestTemplate.exchange(url, method, entity, String.class));
    }
}
