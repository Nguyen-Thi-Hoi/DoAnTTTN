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

public class AllTest {
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
	public void beforeMethod() {
		driver.navigate().refresh();
	}

	@Test
	public void TC_01_Register_Successfully() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("Nguyễn Thị Hội");
		driver.findElement(By.id("user_mobile")).sendKeys("0334884693");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("nguyenthihoi@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Thihoi@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Thihoi@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Da nang");
		driver.findElement(By.name("button")).click();
		driver.findElement(By.linkText("Logout")).click();
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(),
				"Welcome! You have signed up successfully.");

	}

	@Test
	public void TC_02_LoginSuccessFully() {
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("user_email")).sendKeys("nguyenthihoi@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Thihoi@123");

		if (!driver.findElement(By.id("user_remember_me")).isSelected()) {
			driver.findElement(By.id("user_remember_me")).click();
		}
		driver.findElement(By.name("commit")).click();
		// driver.findElement(By.linkText("Logout")).click();
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(), "Signed in successfully.");
	}

	@Test
	public void TC_03_AddCart_Checkout() { // Đã login ; nhập địa chỉ
		// Xem chi tiet SP
		driver.findElement(By.cssSelector(
				"div.show_body:nth-child(5) div.container div.row div.col-sm-9.padding-right div.features_items:nth-child(1) div.col-sm-4.col-xs-6:nth-child(5) div.product-image-wrapper div.single-products div.productinfo.text-center a:nth-child(2) > img.imageProduct"))
				.click();
		// Nhap so luong san pham
		// driver.findElement(By.className("quantity")).clear();
		// driver.findElement(By.className("quantity")).sendKeys("2");
		// chon Size S
		driver.findElement(By.className("active")).click();
		// Them vao gio hang
		driver.findElement(By.name("add-to-cart-detail")).click();
		// vào icon giỏ hàng
		// driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
		// Click button Checkout
		// driver.findElement(By.id("btnFetch")).click();
		// Hien thi popup

		// Click button OK sau khi thanh toán thành công
		// driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
}
