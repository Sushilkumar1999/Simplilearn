package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Excelread {


	//SoftAssert assertobj = new SoftAssert();
	//HardAssert assertobj = new HardAssert();
	ExtentReports extent;
	ExtentTest test;
	
	WebDriver driver;	
    XSSFWorkbook wb;
    XSSFSheet sheet;
	
	@BeforeMethod
	public void setup() throws IOException {
		
		
		System.setProperty("webdriver.chrome.driver", "/home/sushilnayaktecn/Downloads/chromedriver");
        //System.setProperty("webdriver.gecko.driver", "/home/sushilnayaktecn/Downloads/geckodriver");
        
        extent = new ExtentReports ("ExtentReport.html",true);
		
	    driver = new ChromeDriver();
		
		driver.get("https://www.simplilearn.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
		
		FileInputStream file = new FileInputStream("Excellread.xlsx");
		wb = new XSSFWorkbook(file);
		sheet = wb.getSheet("report");
	}

	@Test
	public void login() {
		   test = extent.startTest("Negetive login test");
		
	       WebElement lnkLogin = driver.findElement(By.linkText("Log in"));
			
			lnkLogin.click();
			test.log(LogStatus.PASS, "clicked on login button");
			
			//driver.quit();
			
			WebElement welcomeback = driver.findElement(By.xpath("//*[contains(text(),'Welcome')]")); //to find a string
			//*[contains(text(),'Welcome')]
			WebElement editUsername = driver.findElement(By.xpath("//input[@name='user_login']"));
			String UserName = sheet.getRow(1).getCell(0).getStringCellValue(); 
			editUsername.sendKeys(UserName);
			test.log(LogStatus.PASS, "Entered user name");
			
			WebElement editpwd = driver.findElement(By.xpath("//input[@name='user_pwd']"));
			String Password = sheet.getRow(1).getCell(0).getStringCellValue(); 
			editpwd.sendKeys(Password);
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
	        
	       // Assert.assertEquals(ActError, ExpError);
	        
	       // assertobj.assertEquals(ActError, ExpError);
	        
	       // System.out.println("After Failiure");
	        
	}
	

}
