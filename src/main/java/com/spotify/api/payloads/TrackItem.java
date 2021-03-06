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
public class TrackItem {
    @JsonProperty("album")
    public Album album;

    @JsonProperty("artists")
    public List<Artist> artists;

    @JsonProperty("disc_number")
    public List<Artist> discNumber;

    @JsonProperty("duration_ms")
    public int durationMs;

    @JsonProperty("explicit")
    public boolean explicit;

    @JsonProperty("external_ids")
    public ExternalIds externalIds;

    @JsonProperty("external_urls")
    public ExternalUrls externalUrls;

    @JsonProperty("href")
    public String href;

    @JsonProperty("id")
    public String id;

    @JsonProperty("is_local")
    public boolean isLocal;

    @JsonProperty("is_playable")
    public boolean isPlayable;

    @JsonProperty("name")
    public String name;

    @JsonProperty("popularity")
    public int popularity;

    @JsonProperty("preview_url")
    public String previewUrl;

    @JsonProperty("track_number")
    public int trackNumber;

    @JsonProperty("type")
    public String type;

    @JsonProperty("uri")
    public String uri;
}
