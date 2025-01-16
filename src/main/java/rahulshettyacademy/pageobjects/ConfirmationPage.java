package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	public WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[normalize-space(text())='Thankyou for the order.']")
	WebElement confirmationMessage;

	By conMessage = By.xpath("//label[contains(text(),'Orders')]");

	public String getConfirmationMessage() {
		waitForElementToAppear(confirmationMessage);
		return confirmationMessage.getText();
	}
}
