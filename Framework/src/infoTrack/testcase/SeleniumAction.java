package infoTrack.testcase;

import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumAction {

	private String object;
	private String value;
	private String property;
	private String action;
	WebDriver driver;

	public SeleniumAction(WebDriver driver, String object, String value, String property, String action) {
		this.object = object;
		this.value = value;
		this.property = property;
		this.action = action;
		this.driver = driver;
	}

	public void perform() {
		WebElement webElement;
		switch (this.action) {
		case "EnterText":
			System.out.println("EnterText");
			webElement = findObject();
			if (webElement != null) {
				webElement.clear();
				webElement.sendKeys(this.value);
				waitForMilliSeconds(200);
			}
			break;
		case "Click":
			System.out.println("Click");
			webElement = findObject();
			if (webElement != null) {
				webElement.click();
				waitForMilliSeconds(4000);
			}
			break;

		case "Select":
			try {
				System.out.println("Select");
				webElement = findObject();
				if (webElement != null) {
					Select select = new Select(webElement);
					select.selectByVisibleText(this.value);
					waitForMilliSeconds(2000);
				}
			} catch (Exception e) {
				System.err.println("Failed: Value Not Found : " + this.value);
			}
			break;

		default:
			// System.out.println("No Action Found;");
		}

	}

	public void waitForMilliSeconds(long timeInMilliSeconds)

	{
		try {
			Thread.sleep(timeInMilliSeconds);

		}

		catch (Exception e)

		{

			e.printStackTrace();

		}

	}

	public WebElement findObject() {

		String[] properties = this.property.split(":");

		String attribute = properties[0].trim();

		String attributeValue = properties[1].trim();

		StringTokenizer token = new StringTokenizer(attributeValue, ":");

		attributeValue = token.nextToken().trim();
		while (token.hasMoreTokens()) {
			this.driver.switchTo().frame(token.nextToken().trim());
		}

		int index = 0;

		if (attributeValue.contains(" , ")) {
			token = new StringTokenizer(attributeValue, ",");

			attributeValue = token.nextToken();
			index = Integer.parseInt(token.nextToken().trim());
			index -= 1;
		}
		WebElement webElement = null;
		try {
			List<WebElement> webElements = null;
			switch (attribute) {
			case "name":
				webElements = this.driver.findElements(By.name(attributeValue));
				webElement = webElements.get(index);
				break;
			case "id":
				webElements = this.driver.findElements(By.id(attributeValue));
				webElement = webElements.get(index);
				break;
			case "text":
				webElements = this.driver.findElements(By.linkText(attributeValue));
				webElement = webElements.get(index);
				break;
			case "xpath":
				webElements = this.driver.findElements(By.xpath(attributeValue));
				webElement = webElements.get(index);
				break;
			case "css":
				webElements = this.driver.findElements(By.cssSelector(attributeValue));
				webElement = webElements.get(index);
				break;
			case "class":
				webElements = this.driver.findElements(By.className(attributeValue));
				webElement = webElements.get(index);
				break;

			}
		} catch (Exception e) {
			System.err.println(
					"\nUnable to find [ " + this.object + " ] object with [ " + this.property + " ] property.\n");
			return webElement;
		}
		return webElement;
	}

}
