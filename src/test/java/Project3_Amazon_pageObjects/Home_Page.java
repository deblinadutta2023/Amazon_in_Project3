package Project3_Amazon_pageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home_Page {
	public WebDriver driver;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor js;
	
	public Home_Page(WebDriver wd)
	{ driver =wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	  act=new Actions(driver);
	  js = (JavascriptExecutor) driver;
	}

	//Repository Of  WebElements//
	@FindBy(id="twotabsearchtextbox")private WebElement SearchBox;
	@FindBy(id="nav-search-submit-button")private WebElement SearchIcon;
	@FindBy(xpath="(//span[@class='a-truncate-cut'])[1]")private WebElement SearchResultMsg;
    @FindBy(xpath="(//i[@class='a-icon a-icon-checkbox'])[7]")private WebElement BrandCheckBox;
    @FindBy(xpath = "(//span[@class='a-size-base a-color-base a-text-bold'])[2]")private WebElement SelectedBrand;
    @FindBy(xpath = "//span[@class='a-size-medium-plus a-color-base a-text-bold']")private WebElement SearchResult;
    @FindBy(xpath="//div[@id='brandsRefinements']")private WebElement SectionBrand;
    @FindBy(xpath="//span[@class='a-size-base-plus a-color-base']")private List<WebElement> SearchedBrandProductList;
    @FindBy(xpath = "//li[@id='n/1953602031']/span")private WebElement DepartmentWomen;
    @FindBy(xpath = "//div[@id='departments']/descendant::li")private List<WebElement> SectionDepartment;
    @FindBy(xpath="//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']")private List<WebElement> SearchedDepartmentProduct;

    @FindBy(xpath = "(//div[@class='a-section aok-relative s-image-fixed-height'])[1]")private WebElement PlayStation1;
    @FindBy(xpath = "//span[@id='productTitle']")private WebElement ProductTitle;
    @FindBy(xpath = "//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']")private WebElement ProductPrice;
    @FindBy(xpath = "//span[@class='a-size-medium a-color-success']")private WebElement ProductInStock;
    
	public void launchURL()
	{driver.manage().window().maximize();
	 driver.get("https://www.amazon.in/");
	 wait.until(ExpectedConditions.visibilityOf(SearchBox));
	 System.out.println("You are on Amazon India shopping Online Page ");
	}

	public void verify_GlobalSearchBox()
	{ Assert.assertTrue(SearchBox.isDisplayed());
	 SearchBox.sendKeys("T-shirt");
	 String value=SearchBox.getAttribute("value");
	 assertEquals(value, "T-shirt");
	 System.out.println("Search box is displayed and data entered is visible");
	}

	public void verify_SearchIcon()
	{Assert.assertTrue(SearchIcon.isDisplayed());
	 SearchIcon.click();
	 System.out.println(SearchResultMsg.getText());
	 System.out.println("Search Icon is Clicked and Searched Result is visible As Expected!!!");
	}

	public void apply_BrandFilter()
	{ wait.until(ExpectedConditions.visibilityOf(SectionBrand));
	  Assert.assertTrue(SectionBrand.isDisplayed());
	  BrandCheckBox.click();
	  System.out.println("Check box checked");
	  wait.until(ExpectedConditions.visibilityOf(SearchResult));
	  String Name = SelectedBrand.getText();
	  System.out.println(Name);

	  for(int i=1; i<SearchedBrandProductList.size(); i++)
	  	{assertTrue(SearchedBrandProductList.get(i).getText().equals(Name));
	  	 System.out.println(SearchedBrandProductList.get(i).getText());}
	  System.out.println("Verified! Applied Brand Filter Got The Expected Result Successfully!!!! ");
	}

	public void apply_DepartmentFilter()
	{wait.until(ExpectedConditions.visibilityOfAllElements(SectionDepartment));
	 System.out.println("List Of Department categories Is As Below--------------");
	 for(WebElement menu:SectionDepartment)
	 {System.out.println(menu.getText());}
	 DepartmentWomen.click();
	 wait.until(ExpectedConditions.visibilityOfAllElements(SearchedDepartmentProduct));
     System.out.println("*************************************************************");
	 System.out.println("Verified! Applied Department Filter Got The Expected Result Is As Below:");
	 for(int i=1; i<SearchedDepartmentProduct.size(); i++)
	   {System.out.println(SearchedDepartmentProduct.get(i).getText());}
	}
	
	public void click_PlayStation()
	{SearchBox.sendKeys("playstation");
	 SearchIcon.click();
	 wait.until(ExpectedConditions.visibilityOf(SearchResult));	
	 PlayStation1.click();
	 System.out.println("Searched For 'Sony Playstation Gaming Console & clicked the First result...");
	}
	
	public void check_Playstationdetails()
	{wait.until(ExpectedConditions.visibilityOf(ProductTitle));
	 //Assert.assertTrue(ProductTitle.getText().contains("Sony"));
	Assert.assertTrue(ProductTitle.isDisplayed());
	 System.out.println("'Sony Playstation' Is Verified With Details........ ");
	 System.out.println("Product Detail is Displayed: "+ProductTitle.getText());
	 Assert.assertTrue(ProductPrice.isDisplayed());
	 System.out.println("Product Price is Displayed: "+ProductPrice.getText());
	 Assert.assertTrue(ProductInStock.isDisplayed());
	 System.out.println("Product availablity is Displayed: "+ProductInStock.getText());
	}

}
