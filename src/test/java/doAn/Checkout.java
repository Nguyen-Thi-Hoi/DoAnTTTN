package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkout {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\ChromeDriver\\Version101\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");

		// Dang nhap
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("user_email")).sendKeys("gao@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Gao@12345");
		driver.findElement(By.name("commit")).click();

		// Them vao gio hang thanh cong
		driver.findElement(By.className("add-to-cart")).click();
		// Vao gio hang
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
	}

	@BeforeMethod
	public void beforMethod() {
		driver.navigate().refresh();
	}

	@Test
	public void TC_01_Checkout_When_No_Insert_Address() { // đã login
		// Chon dich vu giao hang; viettel post - $30.0 - Nhận hàng từ 3-5 ngày ; giao
		// hang tiet kiem - $49.0 - Nhận hàng từ 2-3 ngày
		Select dropService = new Select(driver.findElement(By.className("form-select")));
		dropService.selectByVisibleText("viettel post - $30.0 - Nhận hàng từ 3-5 ngày");
		// dien thong tin
		driver.findElement(By.className("btn-warning")).click();
		driver.findElement(By.id("user_address")).sendKeys("Dong Ke - Da Nang");
		// Click button Update
		driver.findElement(By.className("btn-primary")).click();
		// Nhap Voucher
	//	driver.findElement(By.className("voucher_code")).sendKeys("giamgia");
		// Click button Checkout
		driver.findElement(By.id("btnFetch")).click();
		// Click button OK sau khi thanh toán thành công
		// driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

	//	Assert.assertEquals(driver.findElement(By.className("notice")).getText(),
	//			"Your account has been updated successfully.");
	//	Assert.assertEquals(driver.findElement(By.className("swal2-title")).getText(), "Order Success!");

	}

	@Test
	public void TC_02_Checkout_When_Insert_Information() { // Đã login
		// Chon dich vu giao hang
		Select dropService = new Select(driver.findElement(By.className("form-select")));
		dropService.selectByVisibleText("viettel post - $30.0 - Nhận hàng từ 3-5 ngày");

		// Click button Checkout
		driver.findElement(By.id("btnFetch")).click();
		// Click button OK sau khi thanh toán thành công
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).submit();
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
}
