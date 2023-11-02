package Project3_Amazon_pageObjects;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Shopping_Page {
	public WebDriver driver;
	WebDriverWait wait;
	Actions act;
	String parentWindow;


	public Shopping_Page(WebDriver wd)
	{ driver = wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	  act=new Actions(driver);
	  Set<String> Handles=driver.getWindowHandles();
		 Iterator<String> itr=Handles.iterator();
	  parentWindow=itr.next();
	}

	//Repository Of WebElements
	@FindBy(xpath ="//input[@id='twotabsearchtextbox']")private WebElement SearchBox;
	@FindBy(id="nav-search-submit-button")private WebElement SearchIcon;
	@FindBy(xpath = "(//div[@class='a-section aok-relative s-image-tall-aspect'])[2]")private WebElement Tshirt2;
	@FindBy(xpath = "(//div[@class='a-section aok-relative s-image-square-aspect'])[5]")private WebElement OnePlus5;
	@FindBy(xpath = "(//div[@class='a-section aok-relative s-image-square-aspect'])[5]")private WebElement Product3;
	@FindBy(xpath = "(//div[@class='a-section aok-relative s-image-square-aspect'])[6]")private WebElement Product4;
	@FindBy(xpath = "(//div[@class='a-row'])[2]")private WebElement ProductDetailPG;
	@FindBy(xpath = "//select[@id='native_dropdown_selected_size_name']")private WebElement SelectSize;
	@FindBy(xpath = "//input[@id='add-to-cart-button']")private WebElement Add2CartBtn;
	@FindBy(xpath = "//input[@id='buy-now-button']")private WebElement BuyNowBtn;
	@FindBy(xpath = "//div[@class='a-section a-padding-medium sw-atc-message-section']/descendant::span")private WebElement ProductAddedAlert;
	@FindBy(xpath = "//div[@id='NATC_SMART_WAGON_CONF_MSG_SUCCESS']//child::span")private WebElement TshirtAddedCartAlert;
	@FindBy(xpath = "//div[@id='attachDisplayAddBaseAlert']//h4[@class='a-alert-heading'][normalize-space()='Added to Cart']")private WebElement ItemAddedAlert;
	@FindBy(xpath = "//a[@id='attach-close_sideSheet-link']")private WebElement XBtn;
	@FindBy(xpath = "//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']")private WebElement CartButtton;
	@FindBy(xpath = "//span[@class='a-color-price a-text-bold']")private WebElement NotAvailMsg;
	@FindBy(xpath = "//a[text()='Privacy Notice' and 'Conditions of Use']")private WebElement PrivacyNoticeNConditions;
    @FindBy(xpath = "//div[@id='add-to-wishlist-button-group']")private WebElement WishlistButton;
    @FindBy(xpath= "(//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce'])[1]")private WebElement CartBtnFromShopPage;


	public void clickOn_Product1()
	{ wait.until(ExpectedConditions.visibilityOf(Tshirt2));
	  Tshirt2.click();
	  System.out.println("Searched For 'T-shirt' & Clicked on second Result!!");
	}

	public void click_Product2()
	{wait.until(ExpectedConditions.visibilityOf(SearchBox));
	 SearchBox.sendKeys("One Plus");
	 SearchIcon.click();
	 act.moveToElement(OnePlus5).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(OnePlus5));
	 OnePlus5.click();
	 System.out.println("Searched For 'One Plus' mobile Phone & Clicked on Fifth Result!!");
	}

	public void click_Product3()
	{
	 wait.until(ExpectedConditions.visibilityOf(SearchBox));
	 SearchBox.clear();
	 SearchBox.click();
	 SearchBox.sendKeys("Planters");
	 SearchIcon.click();
	 wait.until(ExpectedConditions.visibilityOf(Product3));
	 Product3.click();
	 System.out.println("Searched For 'Planters' & Clicked on First Result!!");
	}

	public void click_Product4()
	{
	 wait.until(ExpectedConditions.visibilityOf(Product4));
	 Product4.click();
	 System.out.println("Searched For 'Planters' & Clicked on Third Result!!");
	}

	public void windowHandling()
	{Set<String> Handles=driver.getWindowHandles();
	 Iterator<String> itr=Handles.iterator();
	 String parentWindow=itr.next();
	 String childWindow=itr.next();
	 driver.switchTo().window(childWindow);
	 System.out.println("Driver is switched To Child Window Successfully!!");
	}
	public void close_CurrentWindow()
	{// closing childWindow(current Window)
	 driver.close();
	 System.out.println("Driver is Closed To Child(Current) Window Successfully!!");
	}

	public void switch_ToParentWindow()
	{// Switching back to parentWindow
	 driver.switchTo().window(parentWindow);
	 System.out.println("Driver is switched Back To Parent Window Successfully!!");
	}

	public void click_AddToCart()  // while Adding Mobiles (Half Screen Added To Cart Screen)
	{ wait.until(ExpectedConditions.visibilityOf(Add2CartBtn));
	 Add2CartBtn.click();
	 wait.until(ExpectedConditions.visibilityOf(CartBtnFromShopPage));
	 Assert.assertTrue(ItemAddedAlert.isDisplayed());
	 System.out.println(ItemAddedAlert.getText());
	 System.out.println("1 Item Is Added To Cart Successfully!!!");
	}

	public void click_AddToCart_Type2() // while Adding Planters & T-shirt(Full Screen Added To Cart Screen)
	{ wait.until(ExpectedConditions.visibilityOf(Add2CartBtn));
	 Add2CartBtn.click();
	 wait.until(ExpectedConditions.visibilityOf(TshirtAddedCartAlert));
	 System.out.println(TshirtAddedCartAlert.getText());
	 Assert.assertTrue(TshirtAddedCartAlert.isDisplayed());
	 System.out.println("1 Item Is Added To Cart Successfully!!!");
	}
	
	public void click_BuyNowButton()
	{wait.until(ExpectedConditions.visibilityOf(BuyNowBtn));
	 Assert.assertTrue(BuyNowBtn.isDisplayed());
	 BuyNowBtn.click();
	 System.out.println("'Buy Now' Button Is Clicked");
	 wait.until(ExpectedConditions.visibilityOf(PrivacyNoticeNConditions));
	 PrivacyNoticeNConditions.isDisplayed();
	 System.out.println("SignIn To Continue The 'Buy Now' Procedure...");
	}
	
	public void click_AddToWishList()
	{wait.until(ExpectedConditions.visibilityOf(WishlistButton));
	 Assert.assertTrue(WishlistButton.isDisplayed());
	 WishlistButton.click();
	 System.out.println("Add To Wishlist Button Is Clicked");
	 wait.until(ExpectedConditions.visibilityOf(PrivacyNoticeNConditions));
	 PrivacyNoticeNConditions.isDisplayed();
	 System.out.println("SignIn To Continue The 'Add to Wishlist' Procedure...");
	}

	public void click_XButton()
	{wait.until(ExpectedConditions.visibilityOf(XBtn));
	 Assert.assertTrue(XBtn.isDisplayed());
	 XBtn.click();
	 System.out.println("'X' Button is clicked.");
	}
}
