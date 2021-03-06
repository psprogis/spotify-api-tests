package com.spotify.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@ToString
public class Image {

    @JsonProperty("height")
    public int height;

    @JsonProperty("url")
    public String url;

    @JsonProperty("width")
    public int width;
}

