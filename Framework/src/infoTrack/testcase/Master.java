package infoTrack.testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Master {

	public static void main(String[] args) throws IOException {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\ykpar\\OneDrive\\Desktop\\NEW\\InfoTrack_extentreports\\TestScript\\extent.html");

		// create ExtentReports and attach reporter(s)
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// adds all log events under it
		ExtentTest test = extent.createTest("Google Login Report");

		String filePath = "C:\\Users\\ykpar\\OneDrive\\Desktop\\InfoTrack_new\\TestScript\\TestSheet.xlsx";

		Excel excel = new Excel(filePath);

		Outcome configurationDetails = excel.getconfiguration();

		Settings settings = new Settings();
		Configuration configuration = settings.setSeleniumSettings(configurationDetails);

		String testCases = configuration.getValueOf("TestCase");
		String url = configuration.getValueOf("URL");

		String browser = configuration.getValueOf("Browser");

		Outcome outcome = excel.getScript(testCases);

		Execution execution = new Execution(url, browser, filePath);
		execution.startBrowser();
		{

			boolean executionStatus = execution.run(outcome);

			if (executionStatus == true) {
				System.out.println("successful Execution");
				test.pass("Successful Execution");

				extent.flush();
				execution.driver.quit();

			}

			else
				System.out.println("Usuccessful");

			execution.driver.quit();

		}
	}
}
