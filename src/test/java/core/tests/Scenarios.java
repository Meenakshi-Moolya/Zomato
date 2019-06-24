package core.tests;

import org.testng.annotations.Test;

public class Scenarios extends SupportTest {
	@Test(priority = 1)
	public void zomato_Login() throws Exception {
		logger.debug(this.getTestStartInfoMessage("LoginPage"));
		zomato.Login();
		logger.debug(this.getTestEndInfoMessage("LoginPage"));

	}
	
	@Test(priority = 2)
	public void zomato_SelectLocation() throws Exception {
		logger.debug(this.getTestStartInfoMessage("SelectLocation"));
		zomato.Login().selectLocation();
		logger.debug(this.getTestEndInfoMessage("SelectLocation"));

	}

	@Test(priority = 3)
	public void zomato_SearchRestaurant() throws Exception {
		logger.debug(this.getTestStartInfoMessage("HomePage"));
		zomato.Login().ZomatoHomePageSearchRestaurant();
		logger.debug(this.getTestEndInfoMessage("HomePage"));

	}

	@Test(priority = 4)
	public void zomato_CheckIfRestaurantDeliversFood() throws Exception {
		logger.debug(this.getTestStartInfoMessage("CheckIfRestaurantDeliversFood"));
		zomato.Login().ZomatoHomePageSearchRestaurant().CheckIfRestaurantDeliversFood();
		logger.debug(this.getTestEndInfoMessage("CheckIfRestaurantDeliversFood"));

	}
}
