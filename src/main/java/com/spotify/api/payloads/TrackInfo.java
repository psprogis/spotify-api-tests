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
public class TrackInfo {

    @JsonProperty("timestamp")
    public long timestamp;

    @JsonProperty("context")
    public Object context;

    @JsonProperty("progress_ms")
    public Object progressMs;

    @JsonProperty("item")
    public TrackItem item;

    @JsonProperty("currently_playing_type")
    public String currentlyPlayingType;

    @JsonProperty("actions")
    public ActionTrack actions;

    @JsonProperty("is_playing")
    public boolean isPlaying;
}
