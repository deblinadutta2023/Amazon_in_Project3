package Project3_Amazon_pageObjects;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Country_Page {
	public WebDriver driver;
	WebDriverWait wait;
	Actions act;

	public Country_Page(WebDriver wd)
	{ driver =wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	  act=new Actions(driver);
	}

	@FindBy(xpath = "//span[@class='icp-nav-link-inner']//span[@class='nav-line-2']")private WebElement SelectLanguage;
	@FindBy(xpath = "//div[@id='nav-flyout-icp']//div[@class='icp-mkt-change-lnk']")private WebElement SelectCountryLink;
	@FindBy(xpath = "//div[@class='a-box-group']/span")private WebElement SelectCountryPg;
	@FindBy(xpath="//span[@class='a-button-text a-declarative']")private WebElement SelectedCountryName;
	@FindBy(xpath="//select[@id='icp-dropdown']")private WebElement SelectCountryDropDown;
    @FindBy(xpath = "//input[@class='a-button-input']")private WebElement GoToWebpage;
    @FindBy(xpath = "//span[@class='icp-nav-flag icp-nav-flag-ca icp-nav-flag-lop']")private WebElement CountryIcon;
    @FindBy(xpath = "//a[@id='icp-nav-flyout']")private WebElement CountryIcon2;
    @FindBy(xpath = "//div[@id='nav-flyout-icp']/descendant::span[9]")private WebElement SelectedCountryMsg;




	public void defaultCountry_Verification()
	{wait.until(ExpectedConditions.visibilityOf(SelectLanguage));
	 act.moveToElement(SelectLanguage).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(SelectCountryLink));
	 SelectCountryLink.click();
	 wait.until(ExpectedConditions.visibilityOf(SelectCountryPg));
	 Assert.assertTrue(SelectCountryPg.isDisplayed());
	 System.out.println("User Is On 'Website (Country/Region)' Page");
	 System.out.println("Default Country Is: "+SelectedCountryName.getText());
	 assertEquals(SelectedCountryName.getText(), "India");
	}

	public void select_CountryToCanada()
	{Select counrty=new Select(SelectCountryDropDown);
	 counrty.selectByVisibleText("Canada");
	 act.sendKeys(Keys.ENTER).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(SelectedCountryName));
	 GoToWebpage.click();
	 // Window handling
	 Set<String>handles=driver.getWindowHandles();
	 Iterator<String> itr=handles.iterator();
	 String parentWindow=itr.next();
	 String childWindow=itr.next();
	 driver.switchTo().window(childWindow);

	 wait.until(ExpectedConditions.visibilityOf(SelectLanguage));
	 act.moveToElement(CountryIcon).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(SelectedCountryMsg));
	 System.out.println("Verified! Amazon Website has Changed According to the Selected Country & has Displayed Message: "+SelectedCountryMsg.getText());
	 assertEquals(SelectedCountryMsg.getText(), "You are shopping on Amazon.ca");
	}

	public void select_CountryToUnitedKingdom()
	{Select counrty=new Select(SelectCountryDropDown);
	 counrty.selectByVisibleText("United Kingdom");
	 act.sendKeys(Keys.ENTER).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(SelectedCountryName));
	 GoToWebpage.click();
	 //Window handling
	 Set<String>handles=driver.getWindowHandles();
	 Iterator<String> itr=handles.iterator();
	 String parentWindow=itr.next();
	 String childWindow=itr.next();
	 driver.switchTo().window(childWindow);

	 wait.until(ExpectedConditions.visibilityOf(SelectLanguage));
	 act.moveToElement(SelectLanguage).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(SelectedCountryMsg)); System.out.
	 println("Verified! Amazon Website has Changed According to the Selected Country & has Displayed Message: "+SelectedCountryMsg.getText());
	 assertEquals(SelectedCountryMsg.getText(),"You are shopping on Amazon.co.uk");
	}
}
