package Project3_Amazon_pageObjects;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Signup_LoginPage {
	public WebDriver driver;
	WebDriverWait wait;
	Actions act;

	public Signup_LoginPage(WebDriver wd)
	{ driver =wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	  act=new Actions(driver);
	}

	//Repository
	@FindBy(xpath="//input[@id='add-to-cart-button']")private WebElement add2Cart;
	@FindBy(xpath="//a[@id='nav-link-accountList']")private WebElement SignInAccListLink;
	@FindBy(xpath="(//a[@class='nav-action-signin-button'])[1]")private WebElement SignInLink;
	@FindBy(xpath="//div[@class='a-box-inner a-padding-extra-large']")private WebElement SignInTextbox;
	@FindBy(id="continue") private WebElement SignInContiueBtn;
	@FindBy(id="continue-announce") private WebElement CreateContiueBtn;
	@FindBy(id="signInSubmit") private WebElement CraeteSubmitBtn;

	@FindBy(id="createAccountSubmit") private WebElement CreateYourAccLink;
	@FindBy(xpath="//div[@class='a-box a-spacing-extra-large']") private WebElement CreateAccountForm;
	@FindBy(id="ap_customer_name")private WebElement FirstLastName;
	@FindBy(id="ap_email")private WebElement CreateEmailId;
	@FindBy(id="ap_phone_number")private WebElement CreatePhoneNum;
	@FindBy(id="auth-country-picker")private WebElement CreateCountryCode;
	@FindBy(id="ap_password")private WebElement CreatePassword;
	@FindBy(xpath ="//input[@id='continue']")private WebElement CreateVerifyMobileNum;
	@FindBy(xpath = "(//div[@class='a-alert-content'])[1]")private WebElement ErrorMsg;

	public void click_SignIn()
	{Assert.assertTrue(SignInAccListLink.isDisplayed());
	 System.out.println("Hello SignIn & Account List is visible");
	 act.moveToElement(SignInAccListLink).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(SignInLink));
	 SignInLink.click();
	 wait.until(ExpectedConditions.visibilityOfAllElements(SignInTextbox));
	 Assert.assertTrue(SignInTextbox.isDisplayed());
	 System.out.println("SignIn EmailId Text Field is Available");
	}

	public void register_NewAccount() throws IOException
	{CreateYourAccLink.click();
	 wait.until(ExpectedConditions.visibilityOfAllElements(CreateAccountForm));
	 Assert.assertTrue(CreateAccountForm.isDisplayed());
	 //XSSFSheet sheet1=AccessExcelSheet.ExcelRegistration();
	 	 String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\3rd_Project\\project3_Amazon.xlsx";
	  FileInputStream fis=new FileInputStream(filePath);
		XSSFWorkbook workBook=new XSSFWorkbook(fis);
		XSSFSheet sheet1=workBook.getSheet("Registration");
	 
	 FirstLastName.sendKeys(sheet1.getRow(1).getCell(0).toString());
	 Select code=new Select(CreateCountryCode);
	 code.selectByValue("IN");
	 CreatePhoneNum.sendKeys(sheet1.getRow(1).getCell(1).getRawValue());
	 CreateEmailId.sendKeys(sheet1.getRow(1).getCell(2).toString());
	 CreatePassword.sendKeys(sheet1.getRow(1).getCell(3).getRawValue());
	 CreateVerifyMobileNum.click();
	 System.out.println("Registration Has filled & Submitted Successfully!");
	}

	public void login_InvalidCredentials() throws InterruptedException
	{CreateEmailId.sendKeys("stughosh@gmail.com");
	 SignInContiueBtn.click();
	 wait.until(ExpectedConditions.visibilityOf(ErrorMsg));
	 System.out.println("Login With Invalid Credentials Has Generated Error Message: "+ErrorMsg.getText());
	 assertEquals(ErrorMsg.getText(), "We cannot find an account with that email address");
	 
	}

}
