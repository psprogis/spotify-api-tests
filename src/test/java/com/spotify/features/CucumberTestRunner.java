package com.spotify.features;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"api.steps"},
        features = "src/test/java/features"
)
public class CucumberTestRunner {}

