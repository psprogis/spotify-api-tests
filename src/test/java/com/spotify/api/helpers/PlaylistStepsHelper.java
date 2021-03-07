package com.spotify.api.helpers;

import com.spotify.api.assertions.AssertableResponse;
import com.spotify.api.payloads.CurrentUserPlaylists;
import com.spotify.api.payloads.Items;
import com.spotify.api.payloads.PlaylistInfo;
import com.spotify.api.payloads.TrackInfoDeletion;
import com.spotify.api.payloads.TrackInfoGeneration;
import com.spotify.api.services.TrackService;
import com.spotify.api.services.UserPlaylistService;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import static com.spotify.api.conditions.Conditions.statusCode;
import static com.spotify.api.conditions.Conditions.bodyCondition;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyOrNullString;

@Slf4j
public class PlaylistStepsHelper {

    @Steps
    TrackService trackService;

    @Steps
    UserStepsHelper userStepsHelper;

    @Steps
    UserPlaylistService userPlaylistService;

    String trackIdDefault = "4Et96UvCqeHItszHzDpXUJ";

    @Step("track addition based on track with id ${0} for playlist ${1} with status ${2}")
    public void generateTrackInfoForTheGivenTrack(String trackId, String playlist, String status) {
        String playlistId = getPlaylistByName(playlist);
        log.info("Selected playlist id " + playlistId);
        TrackInfoGeneration trackInfoGeneration = generateTrackInfo(trackId);
        log.info("Generated track info " + trackInfoGeneration);
        AssertableResponse response = trackService.addTracksToThePlaylist(trackInfoGeneration, playlistId);
        if (status.contains("success")) {
            response.shouldHave(statusCode(201))
                    .shouldHave(bodyCondition("snapshot_id", not(isEmptyOrNullString())));
        } else {
            checkError(response, "Invalid track uri");
        }

    }

    @Step("getting size of playlist for the current user")
    public int getPlaylistSizeForCurrentUser() {
        return userPlaylistService.getCurrentUserPlaylists().asPojo(CurrentUserPlaylists.class).total();
    }

    @Step("getting any existing playlist id")
    public String getFirstPlaylistId() {
        return userPlaylistService.getCurrentUserPlaylists().asPojo(CurrentUserPlaylists.class).items().get(0).id();
    }

    @Step("generate new playlist info for the parameter ${0} and value ${1} for the given playlist ${2}")
    public PlaylistInfo generateInfoForTheUpdate(String parameter, String value, Items items) {
        PlaylistInfo playlistInfo = new PlaylistInfo();
        switch (parameter) {
            case "name":
                playlistInfo.name(value).description(items.description()).publicInfo(items.publicOption());
                break;
            case "description":
                playlistInfo.description(value).name(items.name()).publicInfo(items.publicOption());
                break;
            case "public":
                playlistInfo.publicInfo(Boolean.parseBoolean(value)).name(items.name()).description(items.description());
                break;
            case "all":
                playlistInfo.publicInfo(false).name(value).description(value + "desc");
                break;
        }
        return playlistInfo;

    }

    @Step("update playlist parameter ${1} to value ${2} for object ${3}")
    public void updateGivenPlaylist(String parameter, String newValue, Items item) {
        PlaylistInfo playlistInfo = generateInfoForTheUpdate(parameter, newValue, item);
        userPlaylistService.updatePlaylistName(item, playlistInfo).shouldHave(statusCode(200));
    }

    @Step("create playlist ${0} for the current user")
    public Items createPlaylistForCurrentUser(String playlistName) {
        String userId = userStepsHelper.getCurrentUserId();
        PlaylistInfo body = new PlaylistInfo().description("any").name(playlistName + System.currentTimeMillis()).publicInfo(false);
        log.info("generated playlist information: " + body);
        return userPlaylistService.createNewPlaylistWithName(body, userId)
                .shouldHave(statusCode(201)).asPojo(Items.class);
    }

    @Step("check information for playlist ${0} that parameter ${1} equal to ${2}")
    public boolean verifyParameterInfo(Items item, String typeOfParameter, String newValue) {
        boolean result = false;
        switch (typeOfParameter) {
            case "name":
                result = item.name().contains(newValue);
                break;
            case "description":
                result = item.description().contains(newValue);
                break;
            case "public":
                result = item.publicOption() == Boolean.parseBoolean(newValue);
                break;
            case "all":
                result = item.description().contains(newValue) && item.name().contains(newValue);
        }
        return result;
    }

    @Step("verify that for item ${0} parameter ${1} is changed to ${2}")
    public boolean verifyParameterInformationForItem(Items items, String parameter, String newValue) {
        Items updatedPlaylist = userPlaylistService.getPlaylistById(items.id()).asPojo(Items.class);
        return verifyParameterInfo(updatedPlaylist, parameter, newValue);
    }

    @Step("getting playlist by name ${0}")
    public String getPlaylistByName(String playlistName) {
        return userPlaylistService.getCurrentUserPlaylists().asPojo(CurrentUserPlaylists.class)
                .items().stream().filter(it -> it.name().contains(playlistName))
                .collect(Collectors.toList())
                .get(0).id();
    }

    @Step("getting any playlist for current user")
    public Items getAnyFirstPlaylist() {
        String playlistId = getFirstPlaylistId();
        log.info("First available playlist id is " + playlistId);
        return userPlaylistService.getPlaylistById(playlistId).asPojo(Items.class);
    }

    @Step("generate track info based on given id ${0}")
    public TrackInfoGeneration generateTrackInfo(String trackId) {
        return new TrackInfoGeneration().position(1).uri(prepareTrackId(trackId));
    }

    @Step("get number of tracks for the given playlist ${0} and market ${1}")
    public int getNumberOfTracksForThePlaylist(String playlistName, String market) {
        String playlistId = getPlaylistByName(playlistName);
        log.info("Selected playlist id " + playlistId);
        String userId = userStepsHelper.getCurrentUserId();
        log.info("Current user id " + userId);

        return trackService.getNumberOfTracksInUserPlaylist(userId, playlistId, market).shouldHave(statusCode(200))
                .asPojo(CurrentUserPlaylists.class).total();
    }

    @Step("delete track any from playlist ${0} at position ${1} with status ${2} ")
    public void deleteTrackFromThePlaylist(String playlistName, int position, String status) {
        String playlistId = getPlaylistByName(playlistName);
        log.info("Selected playlist id " + playlistId);
        TrackInfoGeneration trackInfoGeneration = new TrackInfoGeneration().position(position).uri(prepareTrackId("any"));
        TrackInfoDeletion trackInfoDeletion = new TrackInfoDeletion().tracks(Collections.singletonList(trackInfoGeneration));
        AssertableResponse response = userPlaylistService.deleteTracksFromPlaylist(trackInfoDeletion, playlistId);
        if (status.contains("success")) {
            response.shouldHave(statusCode(200));
        } else {
            checkError(response, "Invalid track uri");
        }
    }

    @Step("prepare track id based on given ${0}")
    public String prepareTrackId(String trackId) {
        if (trackId.contains("recent played")) {
            trackId = userStepsHelper.getIdOfRecentListenTrack();
        } else if (trackId.contains("any")) {
            trackId = "spotify:track:" + trackIdDefault;
        }
        return trackId;
    }

    @Step("verify current size ${0} of tracks is ${1} comparing with default ${2} ")
    public boolean verifyCurrentSizeOfTracks(int currentSizeOfTracks, String parameter, int defaultNumberOfTracks) {
        boolean result = false;
        switch (parameter) {
            case "higher":
                result = currentSizeOfTracks > defaultNumberOfTracks;
                break;
            case "equal":
                result = currentSizeOfTracks == defaultNumberOfTracks;
                break;
            case "less":
                result = currentSizeOfTracks < defaultNumberOfTracks;
                break;

        }
        return result;
    }

    @Step("check error in response ${0} contains message ${1}")
    public void checkError(AssertableResponse response, String errorMessage) {
        response.shouldHave(statusCode(400)).shouldHave(bodyCondition("error.message", containsString(errorMessage)));
    }
}
