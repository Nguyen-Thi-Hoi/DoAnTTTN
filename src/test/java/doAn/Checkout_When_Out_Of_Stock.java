package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkout_When_Out_Of_Stock {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");

		// Dang nhap
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("user_email")).sendKeys("nthoi2kk@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Abcdefgh@123");
		driver.findElement(By.name("commit")).click();

	}

	@BeforeMethod
	public void beforMethod() {
		driver.navigate().refresh();
	}

	@Test
	public void TC_04_Checkout_When_Out_Of_Stock() { // Đã login ; nhập địa chỉ
		// Xem chi tiet SP
		driver.findElement(By.xpath(
				"//body/div[2]/section[2]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/a[1]/img[1]"))
				.click();
		// Nhap so luong san pham
		driver.findElement(By.className("quantity")).clear();
		driver.findElement(By.className("quantity")).sendKeys("80");
		// chon Size S
		driver.findElement(By.className("active")).click();
		// Them vao gio hang
		driver.findElement(By.className("add-to-cart-detail")).click();
		// vào icon giỏ hàng
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
		// Click button Checkout
		driver.findElement(By.id("btnFetch")).click();
		// Hien thi popup
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText(),
				"Shopping cart is invalid or Out of stock");
		// Click button OK sau khi thanh toán thành công
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
}
