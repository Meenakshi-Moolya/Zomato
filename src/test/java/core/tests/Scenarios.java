package core.tests;

import org.testng.annotations.Test;

public class Scenarios extends SupportTest {

	@Test(enabled = true, priority = 1)
	public void TC_01_signUpFlow() throws Exception {
		hp.signUp();
	}

	@Test(enabled = true, priority = 2)
	public void TC_02_goForTheTrendingSearch() throws Exception {
		hp.signUp().checkForThetrendingNow();
	}

	@Test(enabled = true, priority = 3)
	public void TC_03_downloadHDContentPauseDelete() throws Exception {
		hp.signUp().downloadHDContentPauseDelete();
	}

	@Test(enabled = true, priority = 4)
	public void TC_04_checkingDownoadAndRemoving() throws Exception {
		hp.signUp().downloadTheVideoVerifyAndRemoveTheVideo();
	}
}
