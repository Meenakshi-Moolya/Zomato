package core.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import agent.IAgent;
import central.Configuration;

public class Zomato extends FullPage {

	public Zomato(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
		super(config, agent, testData);
	}

	/**
	 * This method performs the login action
	 *
	 */
	public Zomato login() throws Exception {
		enterCredentials();
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method performs the sign-up action
	 *
	 */
	public Zomato enterCredentials() throws Exception {
		getControl("emailLogin").click();
		Thread.sleep(3000);
		String text = getControl("verifyLoginTitle").getText();
		if (text.equals("Login with Zomato account")) {
			logger.info("Login page title verified");
		} else {
			logger.info("Wrong page");
		}

		List<WebElement> loginControl = driver.findElements(By.className("android.widget.EditText"));
		loginControl.get(0).sendKeys(getTestData().get("username"));
		loginControl.get(1).sendKeys(getTestData().get("password"));
		getControl("loginButton").click();
		Thread.sleep(3000);
		getControl("skip").click();
		Thread.sleep(3000);
		String loginCheck = getControl("verifyZomatoHomeTitle").getText();
		if (loginCheck.equalsIgnoreCase("YOUR LOCATION")) {
			logger.info("Logged in Successfully");
		} else {
			logger.info("Login Failed");
		}

		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method performs the sign-up action
	 *
	 */
	public Zomato selectLocation() throws Exception {
		getControl("selectLocation").click();
		getControl("enterLocation").enterText(getTestData().get("enterLocation"));
		getControl("selectLocationFromDropDown").getText();
		getControl("selectLocationFromDropDown").click();
		Thread.sleep(3000);
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method implements Restaurant Search method
	 *
	 */
	public Zomato zomatoHomePageSearchRestaurant() throws Exception {
		searchRestaurants();
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method Searches for the restaurants
	 *
	 */
	public void searchRestaurants() throws Exception {
		getControl("search").click();
		Thread.sleep(3000);
		List<WebElement> searchControl = driver.findElements(By.className("android.widget.EditText"));
		searchControl.get(0).click();
		Thread.sleep(3000);
		searchControl.get(0).sendKeys(getTestData().get("searchRest"));
		getControl("selectRestaurant").click();
		Thread.sleep(3000);
	}

	/**
	 * This method checks for the delivery with the restaurant
	 *
	 */
	public Zomato checkIfRestaurantDeliversFood() throws Exception {

		getControl("selectNearbyRestaurant").click();
		Thread.sleep(3000);
		String text = getControl("selectFoodDelivery").getText();

		try {
			if (text.equalsIgnoreCase("Looking for food delivery?")) {
				getControl("selectFoodDelivery").click();
				Thread.sleep(3000);
				addFoodToCart();
			}
		} catch (Exception e) {
			logger.info("Restaurants Not accepting Orders");
			this.getAgent().getMobileDriver().quit();
		}
		Thread.sleep(1000);
		return new Zomato(getConfig(), getAgent(), getTestData());
	}

	/**
	 * This method add food to the Cart
	 *
	 */
	public void addFoodToCart() throws Exception {

		getControl("clickMenu").click();
		Thread.sleep(3000);
		getControl("clickBestsellers").click();
		Thread.sleep(3000);
		getControl("addPizza").click();
		Thread.sleep(3000);
		getControl("addToCart").click();
		Thread.sleep(3000);
		viewCart();
	}

	/**
	 * This method checks for the item in the Cart
	 *
	 */
	public void viewCart() throws Exception {
		getControl("viewCart").click();
		Thread.sleep(3000);
		getControl("itemDetails").getText();
		getControl("itemCost").getText();
	}

}
