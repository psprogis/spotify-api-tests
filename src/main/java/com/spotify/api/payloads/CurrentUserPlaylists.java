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
public class CurrentUserPlaylists {

    @JsonProperty("href")
    private String href;

    @JsonProperty("items")
    private List<Items> items;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("next")
    private int next;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("previous")
    private int previous;

    @JsonProperty("total")
    private int total;

}
