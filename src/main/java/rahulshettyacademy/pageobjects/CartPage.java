package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutBtn;

	@FindBy(xpath = "//ul[@class='cartWrap ng-star-inserted']/descendant::h3")
	private List<WebElement> cartProducts;


	public boolean verifyProductDisplay(String productName) {
		return  cartProducts.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
	}

	public CheckoutPage checkout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}


}
