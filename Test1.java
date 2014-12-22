package demo;


import io.appium.java_client.AndroidKeyCode;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;





public class Test1 {
	String googletext;
    private AppiumDriver driver;
    public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
    public static Properties LOG;
    
    @BeforeClass
    public static void initialize() throws IOException{
        
        // Locate Application Log
           LOG = new Properties();
           FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
                   + "/src/Log4j.properties");
           LOG.load(fs);
           LOG.setProperty("log4j.appender.dest1.File",
                   System.getProperty("user.dir")
                           + "/src/application.log");
           LOG.store(new FileOutputStream(System.getProperty("user.dir")
                   + "/src/Log4j.properties"), null); 
           
           APPLICATION_LOGS.debug("test1");
        
       }
    @Before
    public void setUp() throws Exception {
        // set up appium
    	
    	         // set up appium
    	        
		        initialize();
    	        File classpathRoot = new File(System.getProperty("user.dir"));
    	        File appDir = new File(classpathRoot, "/lib");
    	        File app = new File(appDir, "app-prod_app_icon-debug.apk");
    	        DesiredCapabilities capabilities = new DesiredCapabilities();
    	        capabilities.setCapability("platformName", "Android");
    	        capabilities.setCapability("deviceName","Android");
    	        capabilities.setCapability("platformVersion", "4.4.2");
    	        capabilities.setCapability("app", app.getAbsolutePath());
    	        capabilities.setCapability("appPackage", "com.apn.search.app");
    	        capabilities.setCapability("appActivity", ".SearchActivity2");
    	       driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    	   
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void test() throws InterruptedException{
    	System.out.println("App has been successfully installed");
    	WebElement input = driver.findElementByClassName("android.widget.EditText");
    	input.sendKeys("test");
    	driver.sendKeyEvent(AndroidKeyCode.ENTER);
    	Thread.sleep(5000);
    	driver.navigate().back();
    	driver.findElementByClassName("android.widget.EditText").sendKeys("testing");
    	driver.sendKeyEvent(AndroidKeyCode.ENTER);

    	Thread.sleep(5000);
    	driver.navigate().back();

    	driver.findElementByClassName("android.widget.EditText").click();
    	WebElement DeleteHistory = driver.findElementByName("Clear history");
    	//WebElement DeleteHistory1 = driver.findElementByName("Clear history");

    	DeleteHistory.click();

    	System.out.println("PASS History has been cleared successfully");
     	
  //Search history
    	driver.findElementByClassName("android.widget.ImageView").click();

    	driver.findElementByClassName("android.widget.LinearLayout").click();    	

    	driver.findElementByClassName("android.widget.Switch").click();

    	System.out.println("PASS Search history has been turned off but lets verify it...");	
    	driver.navigate().back();
    	 List<String> Names = new ArrayList<String>();
    	 Names.add("testing");
    	     WebDriverWait waitt = new WebDriverWait(driver, 10);

    	     String Keyword = "testing";
    	     WebElement Field = driver.findElementByClassName("android.widget.EditText");
    	     Field.sendKeys(Keyword);

    	    	driver.sendKeyEvent(AndroidKeyCode.ENTER);
    	    	
    	    	Thread.sleep(3000);
    	    	
    	     // Locate search results
    	    	driver.navigate().back();
    	    	 Field.sendKeys(Keyword);
    	     List<WebElement> ResultsList = driver.findElementByClassName("android.widget.LinearLayout").findElements(By.className("android.widget.TextView"));
    	    waitt.until(ExpectedConditions.visibilityOf(ResultsList.get(0)));
    	      if(ResultsList.equals("Clear history")){
    	       System.out.println("FAIL search history exist");
    	      }
    	      
    	      else{
    	       System.out.println("PASS NO search history exist"); 
    	      }
    	     
    	    
        	    	Thread.sleep(3000);
        	    	

   //configure search suggestions

    	driver.findElementByClassName("android.widget.ImageView").click();

    	driver.findElementByClassName("android.widget.LinearLayout").click();

    	driver.findElementByXPath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]").click();

    	Thread.sleep(6000);
    	TouchAction action = new TouchAction(driver);

    	action.press(0, 512).moveTo(0, 390).release().perform();  

    	
    	System.out.println("Messages has been moved on top of the list");
    	
    	
    	
   //SelectWidgetColor
    	driver.navigate().back();

    	driver.findElementByXPath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[3]").click();

    	driver.findElementByXPath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.CheckedTextView[2]").click();

    	System.out.println("PASS Light theme has been selected");	
    	
    	//Configure turn off
    	driver.findElementByXPath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]").click();

    	driver.findElementByClassName("android.widget.Switch").click();

    	System.out.println("PASS Contacts has been turned off, you won't see it again while searching");	
    	
    	
    	//DRIVER test matching
    	 List<String> eventNames = new ArrayList<String>();
    	 eventNames.add("Cupcake par");
    	 eventNames.add("Dance party");
    	 eventNames.add("Party");
    	 eventNames.add("Pizza par");

    	     WebDriverWait wait = new WebDriverWait(driver, 10);

    	     String searchKeyword = "part";
    	    driver.navigate().back();
    	    driver.navigate().back();
    	     WebElement searchField = driver.findElementByClassName("android.widget.EditText");
    	     searchField.sendKeys(searchKeyword);

    	     // Locate search results
    	 int index = 0;
    	     List<WebElement> searchResultsList = driver.findElementByClassName("android.widget.ListView").findElements(By.className("android.widget.TextView"));
    	     wait.until(ExpectedConditions.visibilityOf(searchResultsList.get(0)));

    	     for(WebElement searchResult : searchResultsList){
    	      if(searchResult.getText().equals(eventNames.get(index++))){
    	       System.out.println("PASS Search result contains search keywords");
    	      }
    	      
    	      else{
    	       System.out.println("FAIL Search result doesn't contain search keywords"); 
    	      }
    	     }

    	System.out.println("Uninstalling the app and cleaning up the data");	
    	
    	
    	
    	

    	     }
    }
    



    	