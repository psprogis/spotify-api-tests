package com.spotify.api;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface ProjectConfig extends  Config {

    String baseUrl();
    String authorizeUrl();
    String refreshToken();
    String clientId();
    String redirectUrl();
    String scope();
}
