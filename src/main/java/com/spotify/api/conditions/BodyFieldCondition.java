package com.spotify.api.conditions;

import io.restassured.response.Response;
import lombok.Data;
import org.hamcrest.Matcher;

@Data
public class BodyFieldCondition implements Condition {

    private final String jsonPath;
    private final Matcher matcher;

    @Override
    public void check(Response response) {
        response.then().assertThat().body(jsonPath, matcher);
    }

    @Override
    public String toString() {
        return "body field  -> [" + jsonPath + "] " + matcher;
    }
}
