package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Logintest {
	
	SoftAssert assertobj = new SoftAssert();
	//HardAssert assertobj = new HardAssert();
	ExtentReports extent;
	ExtentTest test;
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		
		System.setProperty("webdriver.chrome.driver", "/home/sushilnayaktecn/Downloads/chromedriver");
        //System.setProperty("webdriver.gecko.driver", "/home/sushilnayaktecn/Downloads/geckodriver");
        
        extent = new ExtentReports ("ExtentReport.html",true);
		
	    driver = new ChromeDriver();
		
		driver.get("https://www.simplilearn.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
	}
	@Parameters({"uname","password"})
	@Test
	public void login(String username,String password) {
		   test = extent.startTest("Negetive login test");
		
	       WebElement lnkLogin = driver.findElement(By.linkText("Log in"));
			
			lnkLogin.click();
			test.log(LogStatus.PASS, "clicked on login button");
			
			//driver.quit();
			
			WebElement welcomeback = driver.findElement(By.xpath("//*[contains(text(),'Welcome')]")); //to find a string
			//*[contains(text(),'Welcome')]
			WebElement editUsername = driver.findElement(By.xpath("//input[@name='user_login']"));
			
			editUsername.sendKeys("UserName");
			test.log(LogStatus.PASS, "Entered user name");
			
			WebElement editpwd = driver.findElement(By.xpath("//input[@name='user_pwd']"));
			
			editpwd.sendKeys("Password");
			test.log(LogStatus.PASS, "entered password");
			
			WebElement chkbox = driver.findElement(By.xpath("//label[@class='rememberMe']"));
			
			chkbox.click();
			test.log(LogStatus.PASS, "clicked on remember me");
			
			WebElement login = driver.findElement(By.xpath("//input[@name='btn_login']"));
			
			login.click();
			test.log(LogStatus.PASS, "clicked on login button");
			
			WebElement error = driver.findElement(By.id("msg_box"));
	        
	        String ActError = error.getText();
	        
	        String ExpError = "The email or password you have entered is invalid";
	        
	        Assert.assertEquals(ActError, ExpError);
	        
	        assertobj.assertEquals(ActError, ExpError);
	        
	        System.out.println("After Failiure");
	        
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
		extent.close();
	}

}
