package infoTrack.testcase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportGoogle {
	
	ExtentReports extent;
	ExtentTest logger;
	@BeforeTest
	 public void startReport(){
		
	
		//extent = new ExtentReports ();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ykpar\\OneDrive\\Desktop\\NEW\\InfoTrack_extentreports\\TestScript\\extent.html");
	    
        // create ExtentReports and attach reporter(s)
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // creates a toggle for the given test, adds all log events under it    
        ExtentTest test = extent.createTest("Google Login Report");

        // log(Status, details)
        //test.log(Status.INFO, "This step shows usage of log(status, details)");

        // info(details)
        test.info("This step shows usage of info(details)");
        
        // log with snapshot
    //    test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        
        // test with snapshot
      //  test.addScreenCaptureFromPath("screenshot.png");
        
        // calling flush writes everything to the log file
        extent.flush();
    }
}