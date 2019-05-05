package com.google.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import CafeTownSend.testcase.Excel;
import CafeTownSend.testcase.Master;
import CafeTownSend.testcase.Outcome;
import infoTrack.testcase.Configuration;
import infoTrack.testcase.Execution;
import infoTrack.testcase.Settings;

//public class ExcelTest extends Master
public class Login extends Master
{
	public static void main (String[] args) {
	
	//extent = new ExtentReports ();
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ykpar\\OneDrive\\Desktop\\NEW\\InfoTrack_extentreports\\TestScript\\extent.html");
		    
	        // create ExtentReports and attach reporter(s)
	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);

	        // creates a toggle for the given test, adds all log events under it    
	        ExtentTest test1 = extent.createTest("google login reports");

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

			boolean executionStatus = execution.run(outcome);

			if (executionStatus == true)
				//System.out.println("Completed Successfully");
			test1.pass("Test sucessfully Completed");
			else
				System.out.println("Unsuccessful Execution");
		

			execution.driver.quit();
			//test1.log(status.INFO,"starting test");
			test1.pass("Test sucessfully Completed");
			extent.flush();
			
		
	@Test
	public void getTestCaseDataFromExcel() {

		String filePath = "C:\\Users\\ykpar\\OneDrive\\Desktop\\InfoTrack_new\\TestScript\\TestSheet.xlsx";
		Excel excel = new Excel(filePath);
		Outcome outcome = excel.getScript("TestCase");
		Assert.assertTrue(outcome.isSuccessful());
	}

	@Test
	public void checkSheetIsNotBlank() {

		String filePath = "C:\\Users\\ykpar\\OneDrive\\Desktop\\InfoTrack_new\\TestScript\\TestSheet.xlsx";
		Excel excel = new Excel(filePath);
		Outcome outcome = excel.getScript("TestCase");
		System.out.println(outcome.getTotalNumberOfRows());
		Assert.assertEquals(3, outcome.getTotalNumberOfRows());
	}

	@Test
	public void getConfigurationReturnTrue() {

		String filePath = "C:\\Users\\ykpar\\OneDrive\\Desktop\\InfoTrack_new\\TestScript\\TestSheet.xlsx"
		Excel excel = new Excel(filePath);
		Outcome outcome = excel.getconfiguration();
		Assert.assertTrue(outcome.isSuccessful());
	}

	@Test
	public void isBrowserFiledIsPresentInConfigurationReturnTrue() {

	}

}
