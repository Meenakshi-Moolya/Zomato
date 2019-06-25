package core.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import agent.IAgent;
import central.Configuration;
import control.IControl;

public class Zomato extends FullPage {

	public Zomato(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
		super(config, agent, testData);
	}

	/**
	 * This method performs the login action
	 * @return
	 * @throws Exception
	 */
	public Zomato login() throws Exception {
		enterCredentials();
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method performs the sign-up action
	 * @return
	 * @throws Exception
	 */
	public Zomato enterCredentials() throws Exception {
		getControl("emailLogin").click();
		Assert.assertEquals(getControl("verifyLoginTitle").getText(), getTestData().get("loginTitle"), "Login Page is not displayed");
		List<IControl> loginControl = getControls("allEditText");
		loginControl.get(0).enterText(getTestData().get("username"));
		loginControl.get(1).enterText(getTestData().get("password"));
		getControl("loginButton").click();
		getControl("skip").click();
		Assert.assertEquals(getControl("verifyZomatoHomeTitle").getText(), getTestData().get("homeTitle"), "Login is failed");
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method performs the sign-up action 
	 * @return
	 * @throws Exception
	 */
	public Zomato selectLocation() throws Exception {
		getControl("selectLocation").click();
		getControl("enterLocation").enterText(getTestData().get("enterLocation"));
		getControl("selectLocationFromDropDown").getText();
		getControl("selectLocationFromDropDown").click();

		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method implements Restaurant Search method
	 * @return
	 * @throws Exception
	 */
	public Zomato zomatoHomePageSearchRestaurant() throws Exception {
		searchRestaurants();
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method Searches for the restaurants
	 * @throws Exception
	 */
	public void searchRestaurants() throws Exception {
		getControl("search").click();
		List<IControl> searchControl = getControls("allEditText");
		searchControl.get(0).click();
		searchControl.get(0).enterText(getTestData().get("searchRest"));
		getControl("selectRestaurant").click();
	}

	/**
	 * This method checks for the delivery with the restaurant
	 * @return
	 * @throws Exception
	 */
	public Zomato checkIfRestaurantDeliversFood() throws Exception {
		getControl("selectNearbyRestaurant").click();
		String text = getControl("selectFoodDelivery").getText();

		try {
			if (text.equalsIgnoreCase("Looking for food delivery?")) {
				getControl("selectFoodDelivery").click();
				addFoodToCart();
			}
		} catch (Exception e) {
			logger.info("Restaurants Not accepting Orders");
			this.getAgent().getMobileDriver().quit();
		}
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method checks for the delivery with the restaurant
	 * @throws Exception
	 */
	public void addFoodToCart() throws Exception {
		getControl("clickMenu").click();
		getControl("clickBestsellers").click();
		getControl("addPizza").click();
		getControl("addToCart").click();
		viewCart();
	}

	/**
	 * This method checks for the item in the Cart
	 * @throws Exception
	 */
	public void viewCart() throws Exception {
		getControl("viewCart").click();
		getControl("itemDetails").getText();
		getControl("itemCost").getText();
	}

}
