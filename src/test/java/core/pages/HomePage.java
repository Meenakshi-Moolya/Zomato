package core.pages;

import java.util.Map;

import org.testng.Assert;

import agent.IAgent;
import central.Configuration;

public class HomePage extends FullPage {

	public HomePage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
	}

	/**
	 * This method chooses the default language of the contents before the Sign-Up
	 * action
	 *
	 */
	public void chooseLanguage() throws Exception {

		getControl("chooseLanguage").waitUntilVisible();
		getControl("chooseLanguage").click();
	}

	/**
	 * This method performs the Sign-Up action
	 *
	 */
	public HomePage signUp() throws Exception {

		try {
			chooseLanguage();
		} catch (Exception e) {
			logger.info("Choose Language option is not available");
		}

		getControl("hamburgerMenu").waitUntilVisible();
		getControl("hamburgerMenu").click();
		getControl("clickOnSignInLink").waitUntilVisible();
		getControl("clickOnSignInLink").click();
		getControl("continueWithEmail").waitUntilVisible();
		getControl("continueWithEmail").click();
		getControl("enterEmail").enterText(getTestData().get("username"));
		getControl("nextBtn").click();
		getControl("enterPassword").enterText(getTestData().get("password"));
		getControl("signInBtn").click();
		getControl("viuLogoHomePage").waitUntilVisible();
		Assert.assertEquals(true, getControl("viuLogoHomePage").isVisible());

		return new HomePage(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method checks for the trending videos
	 *
	 */
	public void checkForThetrendingNow() throws Exception {

		String trendingName = null;
		getControl("searchBtn").waitUntilVisible();
		getControl("searchBtn").click();
		int listOfTrending = getControls("trendingVideoList").size();

		if (listOfTrending == 0) {
			logger.info("There ae no trending Shows or Movies");
		} else {
			for (int i = 0; i < listOfTrending;) {
				logger.info(getControls("trendingVideoList").get(i).getText());

				trendingName = getControls("trendingVideoList").get(i).getText();
				getControls("trendingVideoList").get(i).click();
				break;
			}
		}
		getControl("trendingVideoFrst").click();
		if (trendingName.contains(getControl("trendingSelection").getText())) {
			logger.info("The selection from the trending list is same");
		} else {
			logger.info("The selection from the trending list is not same");
		}
	}

	/**
	 * This method download HD content Pause and Delete it
	 *
	 */
	public HomePage downloadHDContentPauseDelete() throws Exception {

		getControl("hamburgerMenu").waitUntilVisible();
		getControl("hamburgerMenu").click();
		getControl("moviesDropDown").click();
		getControl("moviesSection").click();
		getControl("selectlanguage").click();
		getControl("hindiLanguage").click();
		getControl("selectContentUnderMovie").click();
		getControl("downloadStart").click();
		getControl("hdQuality").click();
		getControl("closeContentPage").click();
		getControl("downloadBtnHomePage").click();
		getControl("deleteVideoDownloaded").click();
		getControl("clickPauseBtn").click();
		getControl("deleteVideoDownloaded").click();
		getControl("deleteDownloadPopUp").click();

		return new HomePage(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method download the video and remove it from the download list
	 *
	 */
	public void downloadTheVideoVerifyAndRemoveTheVideo() throws Exception {
		getControl("searchBtn").waitUntilVisible();
		getControl("searchBtn").click();
		getControl("enterTextSearchBox").enterText("Kaushiki" + "\\n");
		getControl("clickOnSearchItem").click();
		getControl("downloadStart").click();
		getControl("sdQuality").click();
		getControl("downloadSuccessful").waitUntilVisible();
		getControl("closeContentPage").waitUntilVisible();
		getControl("closeContentPage").click();
		try {
			getControl("downloadIconHomePageDownload").waitUntilVisible();
			getControl("downloadIconHomePageDownload").click();
			getControl("deleteVideoDownloaded").click();
		} catch (Exception e) {
			getControl("downloadBtnHomePage").click();
			getControl("deleteVideoDownloaded").click();
		}
		getControl("deleteDownloadPopUp").click();
		getControl("noDownloadVideoMsg").waitUntilVisible();

		Assert.assertEquals("Oops! No videos", getControl("noDownloadVideoMsg").getText());
	}
}
