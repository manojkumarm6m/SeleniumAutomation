package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	 WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".mb-3")
	WebElement productsBy;

	@FindBy(id = "toast-container")
	WebElement toastMessage;

	By addToCart = By.xpath(".//button[text()=' Add To Cart']");

	public List<WebElement> getProducts() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProducts().stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
	}

	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		driver.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(toastMessage);
	}
}
