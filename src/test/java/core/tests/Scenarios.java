package core.tests;

import org.testng.annotations.Test;

public class Scenarios extends SupportTest {
	@Test(enabled = true, priority = 1)
	public void TC_01_zomato_Login() throws Exception {
		logger.debug(this.getTestStartInfoMessage("LoginPage"));
		zomato.login();
		logger.debug(this.getTestEndInfoMessage("LoginPage"));

	}

	@Test(enabled = true, priority = 2)
	public void TC_02_zomato_SelectLocation() throws Exception {
		logger.debug(this.getTestStartInfoMessage("SelectLocation"));
		zomato.login().selectLocation();
		logger.debug(this.getTestEndInfoMessage("SelectLocation"));

	}

	@Test(enabled = true, priority = 3)
	public void TC_03_zomato_SearchRestaurant() throws Exception {
		logger.debug(this.getTestStartInfoMessage("HomePage"));
		zomato.login().zomatoHomePageSearchRestaurant();
		logger.debug(this.getTestEndInfoMessage("HomePage"));

	}

	@Test(enabled = true, priority = 4)
	public void TC_04_zomato_CheckIfRestaurantDeliversFood() throws Exception {
		logger.debug(this.getTestStartInfoMessage("CheckIfRestaurantDeliversFood"));
		zomato.login().zomatoHomePageSearchRestaurant().checkIfRestaurantDeliversFood();
		logger.debug(this.getTestEndInfoMessage("CheckIfRestaurantDeliversFood"));

	}
}
