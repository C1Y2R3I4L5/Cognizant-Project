package StepDefinition;

import java.io.IOException;

import java.util.Properties;
 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import BaseClass.baseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	WebDriver driver;
	 Properties p;
    
	@Before
   public void setup() throws IOException, InterruptedException
   {
		
	//open the browser	
   	driver=baseClass.initilizeBrowser();
   	    	
   	p=baseClass.getProperties();
   	driver.get(p.getProperty("appURL"));
   	driver.manage().window().maximize();
   	
	}
		
   
   @After
   public void tearDown(Scenario scenario) {
       		
	  //Close the browser
      driver.quit();
      
   }
   
 
   @AfterStep
   public void addScreenshot(Scenario scenario) {
       
   	// this is for cucumber Junit report
       if(scenario.isFailed()) {
       	
	       	TakesScreenshot ts=(TakesScreenshot) driver;
	       	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
	       	scenario.attach(screenshot, "image/png",scenario.getName());
       	            
       }
       else {
    	   
    	   TakesScreenshot ts=(TakesScreenshot) driver;
          	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
          	scenario.attach(screenshot, "image/png",scenario.getName());
          	
       }
     
   }
 
}