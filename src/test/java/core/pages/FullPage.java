package core.pages;

import java.awt.Robot;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import agent.IAgent;
import central.Configuration;
import page.Page;
import pagedef.Identifier;

public abstract class FullPage extends Page {

	public FullPage(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
		super(config, agent, testData);
	}

	WebDriver driver = this.getAgent().getWebDriver();

	Robot r = new Robot();

	protected StringBuffer generateString(int length) {
		StringBuffer output = new StringBuffer();
		String characterSet = "";
		characterSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < length; i++) {
			double index = Math.random() * characterSet.length();
			output.append(characterSet.charAt((int) index));
		}
		return (output);
	}

	protected StringBuffer generateRandomNumber(int length) {
		StringBuffer output = new StringBuffer();
		String characterSet = "";
		characterSet = "1234567890";
		for (int i = 0; i < length; i++) {
			double index = Math.random() * characterSet.length();
			output.append(characterSet.charAt((int) index));
		}
		return (output);
	}
}
