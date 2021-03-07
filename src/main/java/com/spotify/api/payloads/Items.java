package com.spotify.api.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    @JsonProperty("collaborative")
    private boolean collaborative;

    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrlItem;

    @JsonProperty("followers")
    private Followers followers;

    @JsonProperty("href")
    private String href;

    @JsonProperty("id")
    private String id;

    @JsonProperty("images")
    private List<Object> images;

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("public")
    private boolean publicOption;

    @JsonProperty("snapshot_id")
    private String snapshotId;

    @JsonProperty("type")
    private String typeItems;

    @JsonProperty("uri")
    private String uriItems;

    @JsonProperty("primary_color")
    public Object primaryColor;

    @JsonProperty("tracks")
    public TrackItem tracks;
}
