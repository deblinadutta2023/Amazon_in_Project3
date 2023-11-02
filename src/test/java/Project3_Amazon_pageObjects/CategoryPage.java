package Project3_Amazon_pageObjects;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
	public WebDriver driver;
	WebDriverWait wait;
	Actions act;

	public CategoryPage(WebDriver wd)
	{ driver =wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	  act=new Actions(driver);
	}

	@FindBy(xpath="//div[@class='nav-fill']/descendant::a") private List<WebElement> headerList;
	@FindBy(xpath = "//div[@id='nav-main']") private WebElement AllHeaderElements;
	@FindBy(xpath="//select[@id='searchDropdownBox']/option")private List<WebElement> DropDownMenu;
	@FindBy(xpath = "//a[@href='/electronics/b/?ie=UTF8&node=976419031&ref_=nav_cs_electronics']") private WebElement Electronics_Link;
	@FindBy(xpath = "//span[@id='nav-search-label-id']") private WebElement DropDownLabelId;
    @FindBy(xpath = "//a[@href='/mobile-phones/b/?ie=UTF8&node=1389401031&ref_=nav_cs_mobiles']")private WebElement Mobiles_Link;
    @FindBy(xpath="//span[@class='a-truncate-full']")private WebElement searchOnePlus;
    @FindBy(id="twotabsearchtextbox")private WebElement SearchBox;
	@FindBy(id="nav-search-submit-button")private WebElement SearchIcon;
	

	public void verify_DropdownCategoryList()throws IOException
	{ assertEquals(DropDownLabelId.getText(), "All");
	  System.out.println("Amazon India WebPage Categories List As Below-----------");
	  for(WebElement menu:DropDownMenu)
	  {System.out.println(menu.getText());}
	  String filePath="C:\\Deblina_study_personal_doc\\TESTING\\PROJECT_AUTOMATION\\3rd_Project\\project3_Amazon.xlsx";
	  FileInputStream fis=new FileInputStream(filePath);
		XSSFWorkbook workBook=new XSSFWorkbook(fis);
		XSSFSheet sheet1=workBook.getSheet("CategoryList");
	  for(int i=0; i<DropDownMenu.size(); i++) {
		  assertEquals(DropDownMenu.get(i).getText(), sheet1.getRow(1).getCell(i).toString());
	  }	  System.out.println("Dropdown All Category List Is Verified Successfully");
	}

	public void get_HeaderMenu() throws IOException
	{wait.until(ExpectedConditions.visibilityOf(SearchBox));
	 System.out.println("Amazon India WebPage Header menu List As Below-----------");
	 System.out.println(AllHeaderElements.getText());
	 System.out.println("***********************************************************");
	 
	}

	public void searchItem_WithMobilesCategory()
	{Mobiles_Link.click();
	 wait.until(ExpectedConditions.visibilityOf(DropDownLabelId));
	 Assert.assertTrue(DropDownLabelId.getText().equals("Mobiles & Accessories"));
	 SearchBox.sendKeys("One Plus");
	 SearchIcon.click();
	 Assert.assertTrue(searchOnePlus.isDisplayed());
	 System.out.println("Searched Item Under Mobiles category Is: "+searchOnePlus.getText());
	}

	public void search_IrrelevantItemToDropDownCategory()
	{SearchBox.clear();
	 SearchBox.sendKeys("dress");
	 SearchIcon.click();
	 Assert.assertTrue(DropDownLabelId.getText().equals("All"));
	 System.out.println("After searching 'Dress' Dropdown category is Auto Selecting: All");
	}

	public void searchItem_WithElectronicsCategory()
	{Electronics_Link.click();
	 assertEquals(DropDownLabelId.getText(), "Electronics");
	 SearchBox.sendKeys("TV");
	 SearchIcon.click();
	 assertEquals(DropDownLabelId.getText(), "Electronics");
	 System.out.println("After searching 'TV' Dropdown category is Auto Selecting: Electronics");
	}
}
