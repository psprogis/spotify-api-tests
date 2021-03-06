package com.spotify.api.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks {

    @JsonProperty("href")
    private String href;

    @JsonProperty("total")
    private int total;
}
