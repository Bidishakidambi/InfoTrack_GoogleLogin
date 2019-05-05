package infoTrack.testcase;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Execution  {

	Configuration configuration;
	String url;
	String browser;
	public WebDriver driver;
	String filePath;
	private DatasetDetail details;
	private int datasetRowIndex;

	public Execution(String url, String browser, String filePath) {
		this.url = url;
		this.browser = browser;
		this.filePath = filePath;
	}

	public boolean run(Outcome outcome) {
		
		ArrayList<TestScript> testScripts = outcome.getScript();

		for (int index = 0; index < testScripts.size(); index++) {

			TestScript script = testScripts.get(index);

			for (int i = 0; i < script.size(); i++) {
				Row row = testScripts.get(index).getRow(i);
				String object = row.getCellData(0);
				String value = row.getCellData(1);
				if (value.startsWith("{") && value.endsWith("}")) {
					// value = getValue(value);
					value = value.replace("{", "");
					value = value.replace("}", "");
					// String value1 = row.getCellData(1);
					String property = row.getCellData(2);
					String action = row.getCellData(3);
					SeleniumAction seleniumAction = new SeleniumAction(this.driver, object, value, property, action);
					seleniumAction.perform();

				}
				String property = row.getCellData(2);
				String action = row.getCellData(3);

				// System.out.println( object + "\t" + value + "\t" + property + "\t" + action
				// );

				if (object.equalsIgnoreCase("Execute")) {
					StringTokenizer token = new StringTokenizer(value, ":");
					String testScript = token.nextToken();
					String datasetName = token.nextToken();

					Dataset dataset = new Dataset(this.filePath);
					details = dataset.getData(datasetName);

					Excel excel = new Excel(this.filePath);
					Outcome datasetOutcome = excel.getScript(testScript);
					for (int j = 0; j < details.getValues().size(); j++) {
						datasetRowIndex = j;
						this.driver.get(url);
						run(datasetOutcome);
					}
				}

				else if (object.equals("POPUP")) {

					Alert alert = driver.switchTo().alert();
					alert.accept();
					waitForMilliseconds(1000);
				}

				else if (object.equals("REFRESH")) {

					this.driver.navigate().refresh();

				} else if (object.equals("BACK")) {
					this.driver.navigate().back();
					

				} else if (object.equals("CLEAR")) {
					SeleniumAction seleniumAction = new SeleniumAction(this.driver, object, value, property, action);
					WebElement element = seleniumAction.findObject();
					element.clear();
					waitForMilliseconds(1000);

				} else if (object.contains("VERIFY")) {
					StringTokenizer token = new StringTokenizer(object, ":");
					token.nextToken();
					String validator = token.nextToken().trim();
					if (validator.equals("CONTAINS") || validator.equals("TEXT")) {

						String pageText = this.driver.findElement(By.tagName("body")).getText();
						if (pageText.contains(value))
							System.out.println("Pass");
						else
							System.out.println("Fail");
						
					
					} else if (validator.equals("VALUE")) {
						SeleniumAction seleniumAction = new SeleniumAction(this.driver, object, value, property,
								action);
						WebElement element = seleniumAction.findObject();
						String fetchedValue = element.getAttribute("value");
						waitForMilliseconds(3000);
						if (fetchedValue.contains(value))
							System.out.println("Pass");
						else
							System.err.println("Fail : " + "Expected[" + value + "] Actual[" + fetchedValue + "]");
						waitForMilliseconds(1000);
					} else if (validator.equals("OBJECTEXIST")) {

						SeleniumAction seleniumAction = new SeleniumAction(this.driver, object, value, property,
								action);
						WebElement element = seleniumAction.findObject();
						String fetchedValue = "FALSE";
						if (element != null)
							fetchedValue = "TRUE";
						if (fetchedValue.equals(value))
							System.out.println("Pass");
						else {
							System.err.println("Fail");
							Screenshot screen = new Screenshot(driver, this.filePath);
							screen.takeScreenshot();
							waitForMilliseconds(1000);
						}
								 
					} else if (validator.equals("TITLE")) {

						String title = this.driver.getTitle();
						if (title.equals(value))
							System.out.println("Pass");
						else
							System.err.println("Fail : " + "Expected[" + value + "] Actual[" + title + "]");

					} else if (validator.equals("URL")) {

						String url = this.driver.getCurrentUrl();
						if (url.equals(value))
							System.out.println("Pass");
						else
							System.err.println("Fail : " + "Expected[" + value + "] Actual[" + url + "]");

					} else if (validator.equals("CHECKBOX")) {

						SeleniumAction seleniumAction = new SeleniumAction(this.driver, object, value, property,
								action);
						WebElement element = seleniumAction.findObject();
						if (element.isSelected())
							System.out.println("Pass");
						else
							System.err.println("Fail");
					}

				} else if (object.equals("CAPTURESCREENSHOT")) {
					Screenshot screen = new Screenshot(driver, this.filePath);
					screen.takeScreenshot();
				} else if (object.equals("WAIT")) {
					try {
						Thread.sleep(Integer.parseInt(value) * 1000);
					} catch (Exception e) {
						System.out.println("Error in wait");
					}
				} else {
					SeleniumAction seleniumAction = new SeleniumAction(this.driver, object, value, property, action);
					seleniumAction.perform();
				}
			}

		}
		return true;

	}

	private void waitForMilliseconds(long timeInMilliSeconds) {
		try {

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/*
	 * private String getValue(String value) { value = value.replace("{", ""); value
	 * = value.replace("}", ""); //int index = details.getHeader().indexOf(value);
	 * 
	 * int index = details.getValues().indexOf(value); System.out.println(index);
	 * return details.getValues().get(datasetRowIndex).get(index); }
	 */

	public void startBrowser() {

		try {
			if (this.browser.equalsIgnoreCase("IE")) {
				IE ie = new IE();
				this.driver = ie.getDriver();
			} else if (this.browser.equalsIgnoreCase("Chrome")) {
				Chrome chrome = new Chrome();
				this.driver = chrome.getDriver();
			}
			this.driver.get(this.url);

			this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			this.driver.manage().window().maximize();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
