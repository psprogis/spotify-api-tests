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
public class TrackInfoGeneration {

    @JsonProperty("position")
    public int position;

    @JsonProperty("uri")
    public String uri;
}
