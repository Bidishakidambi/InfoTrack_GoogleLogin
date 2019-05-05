package infoTrack.testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox {

	public static void main(String[] args) {

		// Create a new instance of HtmlUnit driver
		WebDriver driver = new FirefoxDriver();

		// Launch the application
		driver.get("http://demo.opensourcecms.com/wordpress/wp-login.php");

		//Maximize the browser
		driver.manage().window().maximize();
		
		//Wait for the object to come
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Get the title of the page
		System.out.println("Page title is: " + driver.getTitle());

		//Close the browser
		driver.close();

	}
}
