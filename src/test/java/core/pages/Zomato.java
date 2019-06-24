package core.pages;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import agent.IAgent;
import central.Configuration;
import io.appium.java_client.MobileElement;

public class Zomato extends FullPage {

	public Zomato(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
		super(config, agent, testData);
		// TODO Auto-generated constructor stub
	}

	public Zomato Login() throws Exception {
		EnterCredentials();
		/*try {
			if (getControl("locationPopup").isVisible()) {
				getControl("clickOK").click();
				EnterCredentials();
			}
		} catch (Exception e) {
			// TODO: handle exception
			EnterCredentials();
		}*/
		return this;
	}
	
	public Zomato EnterCredentials() throws Exception {
		getControl("emailLogin").click();
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
		Thread.sleep(1000);
		getControl("skip").click();
		String loginCheck = getControl("verifyZomatoHomeTitle").getText();
		if (loginCheck.equals("YOUR LOCATION")) {
			logger.info("Logged in Successfully");
		} else {
			logger.info("Login Failed");
		}
		//ZomatoHomePage();
		return this;
	}
	public Zomato selectLocation() throws Exception 
	{		
			getControl("selectLocation").click();
			getControl("enterLocation").enterText(getTestData().get("enterLocation"));
			getControl("selectLocationFromDropDown").getText();
			getControl("selectLocationFromDropDown").click();
			Thread.sleep(1000);
			return this;
		}

	public Zomato ZomatoHomePageSearchRestaurant() throws Exception  {
		searchRestaurants();
		return this;
	}

	public void searchRestaurants() throws Exception {
		getControl("search").click();
		List<WebElement> searchControl = driver.findElements(By.className("android.widget.EditText"));
		searchControl.get(0).click();
		searchControl.get(0).sendKeys(getTestData().get("searchRest"));
		getControl("selectRestaurant").click();
	}

	public Zomato CheckIfRestaurantDeliversFood() throws Exception {

		//getControl("selectRestaurant").click();
		getControl("selectNearbyRestaurant").click();
		getControl("selectFoodDelivery").click();
		/*try {
			if (getControl("closedDelivery").getText().equals("Currently closed for delivery")) {
				System.out.println("Restaurants Not accepting Orders");
				this.getAgent().getMobileDriver().quit();
			}
		} catch (Exception e) {
			// TODO: handle exception
			getControl("selectFoodDelivery").click();
		}*/
		try {
			if (getControl("notDeliverable").getText()
					.equals("This restaurant does not deliver here. Change your delivery address.")) {
				System.out.println("Restaurants Not accepting Orders");
				this.getAgent().getMobileDriver().quit();
			}
		} catch (Exception e) {
			// TODO: handle exception
			addFoodToCart();
		}
		Thread.sleep(1000);
		return this;
	}
	
	public void addFoodToCart() throws Exception {

		getControl("clickMenu").click();
		getControl("clickBestsellers").click();
		getControl("addPizza").click();
		Thread.sleep(1000);
		getControl("addToCart").click();
		viewCart();
	}

	public void viewCart() throws Exception {
		getControl("viewCart").click();
		Thread.sleep(1000);
		getControl("itemDetails").getText();
		getControl("itemCost").getText();
	}

}
