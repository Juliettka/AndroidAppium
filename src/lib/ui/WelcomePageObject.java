package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.Locale;

public class WelcomePageObject extends MainPageObject{
    private static final String
    STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE = "New ways to explore",
    STEP_ADD_OR_EDIT_PREF_LANG = "Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "Learn more about data collected",
    GET_STARTED = "Get started",
    NEXT_BUTTON = "Next";
    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }
    public void waitForLearnMoreLink(){
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_LINK),
                "Cannot find Learn more element",
                10);
    }
    public void clickNextButton(){
        this.waitForElementAndClick(By.id(NEXT_BUTTON),
                "Cannot find Next button",
                10);
    }
    public void clickGetStartedButton(){
        this.waitForElementAndClick(By.id(GET_STARTED),
                "Cannot find Get Started button",
                10);
    }
    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE),
                "Cannot find New ways to explore",
                10);
    }
    public void waitForAddOrEditLangText(){
        this.waitForElementPresent(By.id(STEP_ADD_OR_EDIT_PREF_LANG),
                "Cannot find Add or edit preferred languages",
                10);
    }
    public void waitForLearnMoreDataCollectedText(){
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED),
                "Cannot find Learn more about data collected element",
                10);
    }
}
