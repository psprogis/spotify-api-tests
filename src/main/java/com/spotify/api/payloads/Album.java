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
public class Album {

    @JsonProperty("album_type")
    public String albumType;

    @JsonProperty("artists")
    public List<Artist> artists;

    @JsonProperty("external_urls")
    public ExternalUrls externalUrls;

    @JsonProperty("href")
    public String href;

    @JsonProperty("id")
    public String id;

    @JsonProperty("images")
    public List<Image> images;

    @JsonProperty("name")
    public String name;

    @JsonProperty("release_date")
    public String releaseDate;

    @JsonProperty("release_date_precision")
    public String releaseDatePrecision;

    @JsonProperty("total_tracks")
    public int totalTracks;

    @JsonProperty("type")
    public String type;

    @JsonProperty("uri")
    public String uri;
}

