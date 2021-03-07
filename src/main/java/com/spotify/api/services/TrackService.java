package com.spotify.api.services;

import com.spotify.api.assertions.AssertableResponse;
import com.spotify.api.payloads.TrackInfoGeneration;
import net.thucydides.core.annotations.Step;

public class TrackService extends ApiService {

    String urlForNumberOfTracks = "v1/users/%s/playlists/%s/tracks";
    String urlForTrackAddition = "v1/playlists/%s/tracks";

    @Step("get number of tracks for user ${0} and playlist ${1}")
    public AssertableResponse getNumberOfTracksInUserPlaylist(String userId, String playlistId, String market) {
        return new AssertableResponse(
                setup()
                        .when()
                        .queryParam("market", market)
                        .get(String.format(urlForNumberOfTracks, userId, playlistId)));
    }

    @Step("add new tracks ${0} to playlist ${1} for current user")
    public AssertableResponse addTracksToThePlaylist(TrackInfoGeneration trackInfoGeneration, String playlistId) {
        return new AssertableResponse(
                setup()
                        .queryParam("uris", trackInfoGeneration.uri())
                        .queryParam("position", trackInfoGeneration.position() - 1)
                        .when()
                        .post(String.format(urlForTrackAddition, playlistId)));
    }

    @Step("get track information by id ${0}")
    public AssertableResponse getTrackInfo(String trackId) {
        return new AssertableResponse(
                setup()
                        .queryParam("market", "UA")
                        .when()
                        .get("v1/tracks/" + trackId));
    }
}
