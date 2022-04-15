package ru.gb.restassured.endpoints.shop;

import io.restassured.http.Header;

public abstract class BaseEndpoint {
    String endpoint = this.getClass().getAnnotation(Endpoint.class).value();


    protected Header getAuthHeader(String token) {
        return new Header("Authorization", "Bearer " + token);
    }
}
