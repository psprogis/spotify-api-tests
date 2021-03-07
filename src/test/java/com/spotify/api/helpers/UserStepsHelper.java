package com.spotify.api.helpers;

import com.spotify.api.payloads.CurrentUserInfo;
import com.spotify.api.payloads.TrackInfo;
import com.spotify.api.services.UserService;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class UserStepsHelper {

    @Steps
    UserService userService;

    @Step("get id of current user")
    public String getCurrentUserId() {
        return userService.getCurrentUserInfo().asPojo(CurrentUserInfo.class).id();
    }

    @Step("get currently played track id")
    public String getIdOfRecentListenTrack() {
            return userService.getRecentPlayedTracks().asPojo(TrackInfo.class).item().uri();
        }
}
