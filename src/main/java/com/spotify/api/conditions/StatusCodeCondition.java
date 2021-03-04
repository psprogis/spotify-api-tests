package com.spotify.api.conditions;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class StatusCodeCondition implements Condition {

    private final int statusCode;

    @Override
    public void check(Response response) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Override
    public String toString() {
        return "status code -> " + statusCode;
    }
}
