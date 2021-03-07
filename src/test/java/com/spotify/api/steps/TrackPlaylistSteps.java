package com.spotify.api.steps;

import com.spotify.api.helpers.PlaylistStepsHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class TrackPlaylistSteps {
    @Steps
    PlaylistStepsHelper playlistStepsHelper;

    int defaultNumberOfTracksInPlaylist = 0;

    @When("number of tracks for the playlist {word} is {word} to default")
    public void verify_number_of_tracks_in_playlist(String playlistName, String parameter) {
        int sizeOfTracksForPlaylist = playlistStepsHelper.getNumberOfTracksForThePlaylist(playlistName, "UA");
        boolean result = playlistStepsHelper.verifyCurrentSizeOfTracks(sizeOfTracksForPlaylist, parameter, defaultNumberOfTracksInPlaylist);
        Assert.assertTrue("Actual size of tracks for the playlist " + playlistName + " is not " + parameter + " then " + sizeOfTracksForPlaylist,
                result);
    }

    @When("number of tracks for the playlist {word} is equal to {int}")
    public void get_number_of_tracks_in_playlist(String playlistName, int value) {
        int sizeOfTracksForPlaylist = playlistStepsHelper.getNumberOfTracksForThePlaylist(playlistName, "UA");
        Assert.assertEquals("Actual size of tracks for the playlist " + playlistName + " is equal to " + sizeOfTracksForPlaylist,
                sizeOfTracksForPlaylist, value);
    }


    @Then("delete any track from the playlist {word}")
    public void delete_tracks_from_playlist(String playlistName) {
        playlistStepsHelper.deleteTrackFromThePlaylist(playlistName, defaultNumberOfTracksInPlaylist - 1, "success");
    }

    @Given("get number of tracks for the current user in playlist {word}")
    public void get_tracks_in_playlist(String playlistName) {
        defaultNumberOfTracksInPlaylist = playlistStepsHelper.getNumberOfTracksForThePlaylist(playlistName, "UA");
    }

    @Then("remove track {word} from the playlist with {word}")
    public void remove_track_from_playlist(String playlistName, String parameter) {
        playlistStepsHelper.deleteTrackFromThePlaylist(playlistName, defaultNumberOfTracksInPlaylist - 1, parameter);
    }

    @Then("I reorder tracks with next parameters:")
    public void reorder_tracks(DataTable values) {

    }

    @Then("make sure that tracks reordered successfully")
    public void verify_order_of_tracks() {

    }

}
