package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Cart {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");

		// Dang nhap
		// driver.findElement(By.linkText("Login")).click();
		// driver.findElement(By.id("user_email")).sendKeys("nthoi2kk@gmail.com");
		// driver.findElement(By.id("user_password")).sendKeys("Abcdefgh@123");
		// driver.findElement(By.name("commit")).click();
	}

	@BeforeMethod
	public void beforMethod() {
		driver.navigate().refresh();
	}

	@Test
	public void TC_01_AddToCart() throws InterruptedException {
		driver.findElement(By.className("add-to-cart")).click();
		// vào icon giỏ hàng
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
	}

	@Test
	public void TC_02_Plus_Product() throws InterruptedException {
		driver.findElement(By.cssSelector(
				"div.show_body:nth-child(5) section.col-sm-offset-1.col-sm-10:nth-child(2) div.container div.table-responsive.cart_info table.table.table-condensed tbody.tbody:nth-child(2) tr.list-cart:nth-child(1) td.cart_quantity div.cart_quantity_button > button.cart_quantity_up"))
				.click();
	}

	@Test
	public void TC_03_Minus_Product() {
		driver.findElement(By.cssSelector(
				"div.show_body:nth-child(5) section.col-sm-offset-1.col-sm-10:nth-child(2) div.container div.table-responsive.cart_info table.table.table-condensed tbody.tbody:nth-child(2) tr.list-cart:nth-child(1) td.cart_quantity div.cart_quantity_button > button.cart_quantity_down"))
				.click();
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		Assert.assertEquals(driver.findElement(By.id("swal2-title")).getText(), "Quantity should not be less than 1");

	}

	@Test
	public void TC_04_Enter_Quality_Product() {
		driver.findElement(By.className("cart_quantity_input")).clear();
		driver.findElement(By.className("cart_quantity_input")).sendKeys("4");
	}

	@Test
	public void TC_05_Delete_Item_Product() {
		driver.findElement(By.className("cart_quantity_delete")).click();
	}

	@Test
	public void TC_06_Clear_All_Product() {
		driver.findElement(By.id("clear_cart")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Shopping cart is empty')]")).getText(),
				"Shopping cart is empty");
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
}
