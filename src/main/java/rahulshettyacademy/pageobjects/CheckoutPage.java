package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	public WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryInput;

	@FindBy(xpath = "//button/descendant::span[text()=' India']")
	WebElement countryOption;

	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;

	public void selectCountry(String countryName) {
		waitForElementToAppear(countryInput);
		countryInput.sendKeys(countryName);
		waitForElementToAppear(countryOption);
		countryOption.click();
	}

	public ConfirmationPage clickPlaceOrderButton() {
		waitForElementToAppear(placeOrderButton);
		scrollToElement(placeOrderButton);
		isElementDisplayed(placeOrderButton);
		isElementEnabled(placeOrderButton);
		clickElementUsingJavaScript(placeOrderButton);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
