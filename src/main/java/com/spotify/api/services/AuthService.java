package com.spotify.api.services;

import com.spotify.api.ProjectConfig;
import com.spotify.api.payloads.auth.AuthorizeInfo;
import com.spotify.api.payloads.auth.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import io.restassured.RestAssured;
import net.thucydides.core.annotations.Step;
import org.aeonbits.owner.ConfigFactory;

@Slf4j
public class AuthService {

    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    // TODO: remove me!!!
    private String clientPass = "c5af68bf1ecb4509bce8b85bbff06285";

    @Step("generate token for the current user")
    public TokenInfo generateToken() {
        String code = authorize_user().code();
        return RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(config.clientId(), clientPass)
                .formParam("redirect_uri", config.redirectUrl())
                .formParam("grant_type", "authorization_code")
                .formParam("code", code)
                .post(config.authorizeUrl() + "/api/token").as(TokenInfo.class);
    }

    @Step("authorize current user")
    public AuthorizeInfo authorize_user() {
        // log.info("authorization process is started for the current user with ");

        //TODO: add method to accepting authorization and callback
        return RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(config.clientId(), clientPass)
                .formParam("response_type", "code")
                .formParam("scope", config.scope())
                .formParam("redirect_uri", config.redirectUrl())
                .get(config.authorizeUrl() + "/authorize").as(AuthorizeInfo.class);
    }

    @Step("refresh existing token for the current user")
    public TokenInfo refreshToken() {
        return RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(config.clientId(), clientPass)
                .formParam("grant_type", "refresh_token")
                .formParam("refresh_token", config.refreshToken())
                .post(config.authorizeUrl() + "/api/token").as(TokenInfo.class);
    }


}
