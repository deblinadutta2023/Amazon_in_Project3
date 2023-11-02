package Project3_Amazon_pageObjects;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart_Page {


public WebDriver driver;
WebDriverWait wait;
Actions act;
public Cart_Page(WebDriver wd)
{ driver=wd;
  PageFactory.initElements(driver, this);
  wait=new WebDriverWait(driver, Duration.ofSeconds(10));
  act=new Actions(driver);
}

//Repository
 @FindBy(xpath = "//a[@id='nav-cart']")private WebElement CartLink;
	@FindBy(xpath = "//span[@class='a-truncate-cut']") private List<WebElement> CartItemsList;
	@FindBy(xpath = "(//span[@class='a-dropdown-prompt'])[1]")private WebElement ItemQtyInCart1;
	@FindBy(xpath = "//select[@id='quantity']")private WebElement SelectQty;
	@FindBy(xpath = "(//input[@data-action='save-for-later'])[1]")private WebElement Save4Later;
	@FindBy(xpath = "(//input[@data-action='delete'])[1]")private WebElement DeleteItem;
	@FindBy(xpath = "//div[@id='sc-saved-cart-list-caption-text']")private WebElement SavedItemAlert;
	@FindBy(xpath = "//h2[normalize-space()='Your Amazon Cart is empty']")private WebElement EmptyCartAlert;
	@FindBy(xpath = "//input[@name='proceedToRetailCheckout']")private WebElement Proceed2Checkout;
	@FindBy(xpath="//div[@class='a-box-inner a-padding-extra-large']")private WebElement SignInTextbox1;
	@FindBy(xpath = "//div[@id='claim-collection-container']")private WebElement SignInTextbox2;
	@FindBy(xpath = "//a[text()='Privacy Notice' and 'Conditions of Use']")private WebElement PrivacyNoticeNConditions;

	public void click_CartLink()
	{act.scrollToElement(CartLink).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(CartLink));
	 Assert.assertTrue(CartLink.isDisplayed());
	 CartLink.click();
	 System.out.println("Cart Is visible & Clicked On It.");
	 System.out.println("Items In Cart Is: "+CartItemsList.size());

	}

	public void select_QtyOfProduct()
	{ wait.until(ExpectedConditions.visibilityOf(SelectQty));
	  Select Qty=new Select(SelectQty);
	  Qty.selectByValue("2");
	  System.out.println("Quantity Of Item In Cart has Changed To: "+ItemQtyInCart1.getText());
	  assertEquals(ItemQtyInCart1.getText(), "2");
	}

	public void click_SaveForLater()
	{wait.until(ExpectedConditions.visibilityOf(Save4Later));
	 Assert.assertTrue(Save4Later.isDisplayed());
	 Save4Later.click();
	 System.out.println("Item Has 'Saved For Later' List");
	 wait.until(ExpectedConditions.visibilityOf(SavedItemAlert));
	 System.out.println(SavedItemAlert.getText());
	 assertEquals(SavedItemAlert.getText(), "Saved for later (1 item)");
	}

	public void click_DeleteButton()
	{wait.until(ExpectedConditions.visibilityOf(DeleteItem));
	 Assert.assertTrue(DeleteItem.isDisplayed());
	 DeleteItem.click();
	 System.out.println("1 Item Has Deleted Successfully");
	 driver.navigate().refresh();
	 System.out.println("Items in Cart: "+CartItemsList.size());
	 if(CartItemsList.size()>0)
	  {System.out.println("Items Remaining In Cart: "+CartItemsList.size() );
	  }else
	    {System.out.println(EmptyCartAlert.getText());}
	 }

	public void click_ProceedToCheckout()
	{wait.until(ExpectedConditions.visibilityOf(Proceed2Checkout));
	 Assert.assertTrue(Proceed2Checkout.isDisplayed());
	 Proceed2Checkout.click();
	 System.out.println("Items Does Proceed To Checkout");
	 wait.until(ExpectedConditions.visibilityOf(PrivacyNoticeNConditions));
	 Assert.assertTrue(PrivacyNoticeNConditions.isDisplayed());
	 System.out.println("SignIn To Continue The CheckOut Procedure...");
	}
	
}