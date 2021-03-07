package com.spotify.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@ToString
public class TrackInfoDeletion {

    @JsonProperty("tracks")
    List<TrackInfoGeneration> tracks;
}
