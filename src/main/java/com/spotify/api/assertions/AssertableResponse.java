package com.spotify.api.assertions;

import com.spotify.api.conditions.Condition;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

    private final Response response;

    @Step("api response should have {condition}")
    public AssertableResponse shouldHave(Condition condition) {
        log.info("should check condition: {}", condition);

        condition.check(response);

        return this;
    }

    public <T> T asPojo(Class<T> tClass) {
        return response.as(tClass);
    }


    public <T> List<T> asPojoList(Class<T> tClass) {
        return response.body().jsonPath().getList("", tClass);
    }

    public Headers headers() {
        return response.getHeaders();
    }
}
