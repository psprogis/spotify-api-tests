package com.spotify.api.services;

import com.spotify.api.assertions.AssertableResponse;
import com.spotify.api.payloads.Items;
import com.spotify.api.payloads.PlaylistInfo;
import com.spotify.api.payloads.TrackInfoDeletion;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;

@Slf4j
public class UserPlaylistService extends ApiService {

    @Step("get current user playlists")
    public AssertableResponse getCurrentUserPlaylists() {
        return new AssertableResponse(
                setup().when()
                        .get("v1/me/playlists"));
    }

    @Step("create playlist with info ${0} for user ${1}")
    public AssertableResponse createNewPlaylistWithName(PlaylistInfo playlistInfo, String userId) {
        String urlToCreatePlaylist = "v1/users/%s/playlists";
        return new AssertableResponse(
                setup().when()
                        .body(playlistInfo)
                        .post(String.format(urlToCreatePlaylist, userId)));
    }

    @Step("get playlists by id ${0}")
    public AssertableResponse getPlaylistById(String playlistId) {
        return new AssertableResponse(
                setup().when()
                        .get(String.format("v1/playlists/%s", playlistId)));
    }

    @Step("update playlist for object ${0} with next info ${1}")
    public AssertableResponse updatePlaylistName(Items item, PlaylistInfo playlistInfo) {
        return new AssertableResponse(
                setup().when()
                        .body(playlistInfo)
                        .put(String.format("v1/playlists/%s", item.id())));
    }

    @Step("delete playlist for object ${0} with next info ${1}")
    public AssertableResponse deleteTracksFromPlaylist(TrackInfoDeletion trackInfoDeletion, String playlistId) {
        return new AssertableResponse(
                setup().when()
                        .body(trackInfoDeletion)
                        .delete(String.format("v1/playlists/%s/tracks", playlistId)));
    }
}
