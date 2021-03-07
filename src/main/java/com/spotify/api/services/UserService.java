package com.spotify.api.services;

import com.spotify.api.assertions.AssertableResponse;
import com.spotify.api.payloads.CurrentUserInfo;
import com.spotify.api.payloads.TrackInfo;
import net.thucydides.core.annotations.Step;

public class UserService extends ApiService {

    @Step("get current user info")
    public AssertableResponse getCurrentUserInfo() {
        return new AssertableResponse(setup().when()
                .get("v1/me"));
    }

    @Step("get id of current user")
    public String getCurrentUserId() {
        return getCurrentUserInfo().asPojo(CurrentUserInfo.class).id();
    }

    @Step("get recent played track for the current user")
    public AssertableResponse getRecentPlayedTracks() {
        return new AssertableResponse(setup().when()
                .formParam("market", "UA")
                .get("v1/me/player/currently-playing"));
    }

    @Step("get currently played track id")
    public String getIdOfRecentListenTrack() {
        return getRecentPlayedTracks().asPojo(TrackInfo.class).item().id();
    }
}
