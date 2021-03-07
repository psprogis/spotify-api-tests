package com.spotify.api.services;

import com.spotify.api.ProjectConfig;
import com.spotify.api.helpers.LoginHelper;
import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.util.List;
import org.aeonbits.owner.ConfigFactory;

@Slf4j
public class ApiService {
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    protected RequestSpecification setup() {

        String token = LoginHelper.getToken();

        RestAssured.baseURI = config.baseUrl();
        return RestAssured
                .given()
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .filters(getFilters());
    }

    private List<Filter> getFilters() {
        return Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
