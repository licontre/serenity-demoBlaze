package com.demoblaze.runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features= {"src/test/resources/features.demoblaze/BuyOneLaptop.feature"},glue = {
        "com.demoblaze.steps"})
public class DemoBlazeRunnerTest {
}