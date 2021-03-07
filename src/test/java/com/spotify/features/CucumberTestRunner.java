package com.spotify.features;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"com.spotify.api"},
        features = "src/test/java/com/spotify/features",
        tags = "not @ToImplement"
)
public class CucumberTestRunner {}
