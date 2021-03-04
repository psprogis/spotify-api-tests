package com.spotify.api.conditions;

import io.restassured.response.Response;

/**
 * Checks parameters for compliance
 * with certain conditions
 */
public interface Condition {

    void check(Response response);
}
