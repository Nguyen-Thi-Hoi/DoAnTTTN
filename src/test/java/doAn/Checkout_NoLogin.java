package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkout_NoLogin {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");
	}

	@Test
	public void TC_01_Checkout() {
		// Them vao gio hang thanh cong
		driver.findElement(By.className("add-to-cart")).click();
		// Vao gio hang
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
		// Click button Checkout
		driver.findElement(By.className("btn-default")).click();
		// Click button OK trên popup
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

		// Popup khi Click button Check khi chưa Login
		Assert.assertEquals(driver.findElement(By.id("swal2-title")).getText(), "You Must Login To The Checkout");
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
}
