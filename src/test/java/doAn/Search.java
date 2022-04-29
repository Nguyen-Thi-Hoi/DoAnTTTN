package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Search {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\ChromeDriver\\Version101\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");
	}

	@BeforeMethod
	public void beforMethod() {
		driver.navigate().refresh();
	}

	@Test
	public void TC_01_SearchInData() {
		driver.findElement(By.cssSelector("#search-product")).sendKeys("áo");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.RETURN);
		action.perform();
		Assert.assertEquals(driver.findElement(By.className("features_items")).getText(), "Your product not find");

	}

	@AfterTest
	public void Close() {

	}
}
