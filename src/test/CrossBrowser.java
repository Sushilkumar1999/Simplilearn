package test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;



public class CrossBrowser {
	
	WebDriver ChromeDriver;
	WebDriver FirefoxDriver;

	@Test
	public void lunchChrome() throws MalformedURLException {
		DesiredCapabilities obj = new DesiredCapabilities();
		obj.setPlatform(Platform.LINUX);
		obj.setBrowserName("chrome");
		
		String HubURL = "http://localhost:4444/wd/hub";
		
		ChromeDriver = new RemoteWebDriver(new URL(HubURL),obj);
		
		ChromeDriver.get("https://www.simplilearn.com/");
		ChromeDriver.quit();
		
	}
	@Test(dependsOnMethods="lunchChrome")
	public void testcase1() {
		
	       WebElement lnkLogin = ChromeDriver.findElement(By.linkText("Log in"));
			
			lnkLogin.click();
		
	}
	@Test
	public void lunchFirefox() throws MalformedURLException {
		
		DesiredCapabilities obj = new DesiredCapabilities();
		obj.setPlatform(Platform.LINUX);
		obj.setBrowserName("firefox");
		
		String HubURL = "http://localhost:4444/wd/hub";
		
		FirefoxDriver  = new RemoteWebDriver(new URL(HubURL),obj);
		
		FirefoxDriver.get("https://www.simplilearn.com/");
		FirefoxDriver.quit();
		
	}
	@Test(dependsOnMethods="lunchFirefox")
	public void testcase2() {
		
	       WebElement lnkLogin = FirefoxDriver.findElement(By.linkText("Log in"));
			
			lnkLogin.click();
		
	}
}
