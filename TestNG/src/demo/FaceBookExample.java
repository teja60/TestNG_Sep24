package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FaceBookExample {
	WebDriver driver;
	@BeforeTest
	@Parameters("browser")
		public void openApplication(String browser) {
		if(browser.equals("Chrome")) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	}
		else if (browser.equals("Edge")) {
		System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
		driver = new EdgeDriver();
	}
		else if (browser.equals("Firefox")) {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver = new FirefoxDriver();
	}
		else if (browser.equals("IE")) {
		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
		
		driver.get("https://en-gb.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	public void verifyTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Facebook – log in or sign up";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test
	public void verifyText() {
		String actualText = driver.findElement(By.cssSelector("#content > div > div > div > div._8esl > h2")).getText();
		String expectedText = "Facebook helps you connect and share with the people in your life.";
		Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void verifyLoginButton() {
		boolean loginButton = driver.findElement(By.name("login")).isDisplayed();
		Assert.assertTrue(loginButton);
	}
	
	@AfterTest
	public void closeApplication() {
		driver.close();
	}

}
