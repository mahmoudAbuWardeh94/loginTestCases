import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPagePOM {
	WebDriver driver;
	WebDriverWait wait;

	public LoginPagePOM(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	By userNameInput = By.name("user-name");
	By passwordInput = By.name("password");
	By loginButton = By.id("login-button");
	By validationValue = By.xpath("//h3[@data-test='error']");

	public void enterUsername(String username) {
		WebElement userNameField = wait.until(ExpectedConditions.presenceOfElementLocated(userNameInput));
		userNameField = driver.findElement(userNameInput);
		userNameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
		passwordField = driver.findElement(passwordInput);
		passwordField.sendKeys(password);
	}

	public void clickOnLoginButton() {
		WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
		loginBtn = driver.findElement(loginButton);
		loginBtn.click();
	}

	public String actualValidationMsg() {

		WebElement validationvalue = wait.until(ExpectedConditions.presenceOfElementLocated(validationValue));
		validationvalue = driver.findElement(validationValue);
		return validationvalue.getText();
	}

	public void implicitlyWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

}
