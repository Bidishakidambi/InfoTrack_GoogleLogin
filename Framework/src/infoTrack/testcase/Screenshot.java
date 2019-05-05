package infoTrack.testcase;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	WebDriver driver;
	String filePath;
	public Screenshot(WebDriver driver, String filePath) {
		this.driver = driver;
		this.filePath = filePath;
	}

	public void takeScreenshot() {
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Time time = new Time();
		try {
			String basePath = (String) this.filePath.subSequence(0, this.filePath.lastIndexOf("\\"));
			basePath = basePath + "\\Screenshot";
			
			File file = new File( basePath );
			if( !file.exists() )
				file.mkdir();
			
			String path = basePath + "\\Image_(" + time.getTime() + ").png";
			
			FileUtils.copyFile(sourceFile, new File(path));
			//System.out.println("screenshot succesful");
		} catch (IOException e) {
			System.out.println("Error in taking screenshot.");
		} 
	}

}
