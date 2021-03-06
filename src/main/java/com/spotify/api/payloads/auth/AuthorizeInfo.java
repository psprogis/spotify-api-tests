package com.spotify.api.payloads.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@ToString
public class AuthorizeInfo {
    @JsonProperty("code")
    private String code;

    @JsonProperty("state")
    private String state;
}
