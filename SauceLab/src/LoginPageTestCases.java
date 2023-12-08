import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageTestCases {
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void preTest() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void invalid_username_invalid_password() {
		LoginPagePOM loginPagePOM = new LoginPagePOM(driver);

		loginPagePOM.enterUsername("mah");
		loginPagePOM.enterPassword("1234");
		loginPagePOM.clickOnLoginButton();

		loginPagePOM.actualValidationMsg();
		String expectedValidationMsg = "Epic sadface: Username and password do not match any user in this service";
		softassert.assertEquals(expectedValidationMsg, loginPagePOM.actualValidationMsg());
		softassert.assertAll();
	}

	@Test(priority = 2)
	public void invalid_username_valid_password() {
		LoginPagePOM loginPagePOM = new LoginPagePOM(driver);
		loginPagePOM.enterUsername("mah");
		loginPagePOM.enterPassword("secret_sauce");
		loginPagePOM.clickOnLoginButton();

		loginPagePOM.actualValidationMsg();
		String expectedValidationMsg = "Epic sadface: Username and password do not match any user in this service";
		softassert.assertEquals(expectedValidationMsg, loginPagePOM.actualValidationMsg());
		softassert.assertAll();

	}

	@Test(priority = 3)
	public void valid_username_invalid_password() {
		LoginPagePOM loginPagePOM = new LoginPagePOM(driver);
		loginPagePOM.enterUsername("standard_user");
		loginPagePOM.enterPassword("1234");
		loginPagePOM.clickOnLoginButton();

		loginPagePOM.actualValidationMsg();
		String expectedValidationMsg = "Epic sadface: Username and password do not match any user in this service";
		softassert.assertEquals(expectedValidationMsg, loginPagePOM.actualValidationMsg());
		softassert.assertAll();
	}

	@Test(priority = 4)
	public void empty_username_empty_password() {
		LoginPagePOM loginPagePOM = new LoginPagePOM(driver);
		loginPagePOM.enterUsername("");
		loginPagePOM.enterPassword("");
		loginPagePOM.clickOnLoginButton();
		WebElement emptyGetValue = driver.findElement(By.xpath("//h3[@data-test='error']"));
		String actualEmptyValidationMsg = emptyGetValue.getText();
		String expectedEmptyValidationMsg = "Epic sadface: Username is required";
		softassert.assertEquals(actualEmptyValidationMsg, expectedEmptyValidationMsg);
		softassert.assertAll();

	}

	@Test(priority = 5)
	public void valid_username_valid_password() {
		LoginPagePOM loginPagePOM = new LoginPagePOM(driver);
		loginPagePOM.enterUsername("standard_user");
		loginPagePOM.enterPassword("secret_sauce");
		loginPagePOM.clickOnLoginButton();

		WebElement logo = driver.findElement(By.xpath("//div[@class='peek']"));
		softassert.assertTrue(logo.isDisplayed());
		softassert.assertAll();
	}

	@AfterMethod()
	public void afterMethod() {
		driver.quit();
	}

}
