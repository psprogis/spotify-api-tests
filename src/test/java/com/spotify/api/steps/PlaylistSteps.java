package com.spotify.api.steps;

import com.spotify.api.helpers.PlaylistStepsHelper;
import com.spotify.api.payloads.Items;
import com.spotify.api.services.UserPlaylistService;
import com.spotify.api.services.UserService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import static com.spotify.api.conditions.Conditions.statusCode;

@Slf4j
public class PlaylistSteps {

    @Steps
    PlaylistStepsHelper playlistStepsHelper;

    Items currentUserPlaylistItems;

    int playlistSize = 0;

    @Given("get current size of playlists for the current user")
    public void get_current_size_playlists() {
        playlistSize = playlistStepsHelper.getPlaylistSizeForCurrentUser();
        log.info("Number of playlists is " + playlistSize);
    }

    @When("I choose any predefined playlist")
    public void choose_playlist_with_id() {
        currentUserPlaylistItems = playlistStepsHelper.getAnyFirstPlaylist();
    }

    @When("make sure that current playlists size above {int}")
    public void verify_number_of_playlists(int size) {
        Assert.assertTrue("Current playlists size is " + playlistSize + "it's less then " + size,
                playlistSize > size);
    }

    @Then("I create new playlist with name {word} for the current user")
    public void create_new_playlist(String playlistName) {
        currentUserPlaylistItems = playlistStepsHelper.createPlaylistForCurrentUser(playlistName);
    }

    @Then("current size of playlists increased")
    public void current_size_of_playlist() {
        int actPlaylistSize = playlistStepsHelper.getPlaylistSizeForCurrentUser();
        Assert.assertTrue("Current playlists size is " + actPlaylistSize + "it's not more then previous" + playlistSize,
                playlistSize < actPlaylistSize);
    }

    @Then("update {word} of playlist to {word}")
    public void update_name_playlist(String param, String newPlaylistName) {
        playlistStepsHelper.updateGivenPlaylist(param, newPlaylistName, currentUserPlaylistItems);
    }

    @Then("make sure that updates applied successfully: {word} updated to {word}")
    public void verify_update_successfully(String typeOfParameter, String updatedValue) {
        Assert.assertTrue("For the given playlist value of parameter " + typeOfParameter + " isn't equal to " + updatedValue,
                playlistStepsHelper.verifyParameterInformationForItem(currentUserPlaylistItems, typeOfParameter, updatedValue));
    }

    @Then("I add {word} new tracks to the existing playlist {word} for the current user with {word}")
    public void add_new_tracks_to_existing_playlist(String trackIds, String playlistName, String param) {
        playlistStepsHelper.generateTrackInfoForTheGivenTrack(trackIds, playlistName, param);
    }
}
