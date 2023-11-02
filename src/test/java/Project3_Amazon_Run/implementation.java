package Project3_Amazon_Run;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Project3_Amazon_pageObjects.Cart_Page;
import Project3_Amazon_pageObjects.CategoryPage;
import Project3_Amazon_pageObjects.Country_Page;
import Project3_Amazon_pageObjects.Home_Page;
import Project3_Amazon_pageObjects.LanguagePage;
import Project3_Amazon_pageObjects.Shopping_Page;
import Project3_Amazon_pageObjects.Signup_LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
public class implementation {
	public WebDriver driver;
	Home_Page homePG;
	Signup_LoginPage register;
	LanguagePage lang;
	Country_Page country;
	CategoryPage category;
	Shopping_Page shopAmz;
	Cart_Page  cartAmz;

	@BeforeMethod
	public void setUp_Browser() throws InterruptedException
	{ WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  homePG=new Home_Page(driver);
	  register=new Signup_LoginPage(driver);
	  lang=new LanguagePage(driver);
	  country=new Country_Page(driver);
	  category=new CategoryPage(driver);
	  shopAmz=new Shopping_Page(driver);
	  cartAmz=new Cart_Page(driver);
	}
  @Test(priority=1)
  public void homepage_validation() {
	  homePG.launchURL();
	  assert driver.getTitle().contains("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in") : "Failed to launch Amazon India website";
  }
  @Test(priority=2)
  public void signInLink_Verification() {
	  homePG.launchURL();
	  register.click_SignIn();
  }
  @Test(priority=3)
  public void register_NewUserAccount() throws IOException {
	  homePG.launchURL();
	  register.click_SignIn();
	  register.register_NewAccount();
  }
  @Test(priority=4)
  public void allCategoryList_Verification() throws IOException {
	  homePG.launchURL();
	  category.get_HeaderMenu();
	  category.verify_DropdownCategoryList();
  }
  @Test(priority=5)
  public void globalSearchBox_Verification() {
	  homePG.launchURL();
	  homePG.verify_GlobalSearchBox();
	  homePG.verify_SearchIcon();
  }
  @Test(priority=6)
  public void categoryMobiles_Verification() {
	  homePG.launchURL();
	  category.searchItem_WithMobilesCategory();
	  category.search_IrrelevantItemToDropDownCategory();
  }
  @Test(priority=7)
  public void categoryElectronics_Verification() {
	  homePG.launchURL();
	  category.searchItem_WithElectronicsCategory();
	  category.search_IrrelevantItemToDropDownCategory();
  }
  @Test(priority=8)
  public void KannadaLanguage_Validation() {
	  homePG.launchURL();
      lang.language_Verification();
      lang.select_TamilLanguage();
  }
  @Test(priority=9)
  public void MarathiLanguage_Validation() {
	  homePG.launchURL();
      lang.language_Verification();
      lang.select_BengaliLanguage();
  }
  @Test(priority=10)
  public void CountryLanguageList_Validation() {
	  homePG.launchURL();
      lang.language_Verification();
      lang.countryLanguages();
  }

  @Test(priority=11)
  public void loginWithInvalidCredentials_Verifiaction() throws InterruptedException {
	  homePG.launchURL();
	  register.click_SignIn();
	  register.login_InvalidCredentials();
  }
  @Test(priority=12)
  public void changeCountryToCanada_Verifiaction() {
	  homePG.launchURL();
	  country.defaultCountry_Verification();
	  country.select_CountryToCanada();
  }
  @Test(priority=13)
  public void changeCountryToUnitedKingdom_Verifiaction() {
	  homePG.launchURL();
	  country.defaultCountry_Verification();
	  country.select_CountryToUnitedKingdom();
  }
  @Test(priority=14)
  public void searchProductByFilter_Validation()  //expected [true] but found [false]
  {   homePG.launchURL();
      homePG.verify_GlobalSearchBox();
      homePG.verify_SearchIcon();
      homePG.apply_BrandFilter();
      homePG.apply_DepartmentFilter();
  }
  
  @Test(priority=15)
  public void addProductsToCart_Verification()
  { homePG.launchURL();
    shopAmz.click_Product3();
    shopAmz.windowHandling(); //parentWindow To childWindow
    shopAmz.click_AddToCart_Type2();
    shopAmz.close_CurrentWindow();  // closing childWindow(current Window)
    shopAmz.switch_ToParentWindow(); // Switching back to parentWindow
    shopAmz.click_Product4();
    shopAmz.windowHandling();
    shopAmz.click_AddToCart_Type2();
    cartAmz.click_CartLink();

  }
  @Test(priority=16)
  public void productQuantityInCart_Verification()
  { homePG.launchURL();
    shopAmz.click_Product2();
    shopAmz.windowHandling();
    shopAmz.click_AddToCart();
    shopAmz.click_XButton();
    cartAmz.click_CartLink();
    cartAmz.select_QtyOfProduct();
  }
  @Test(priority=17)
  public void saveProductForlaterList_Verification()
  {homePG.launchURL();
  shopAmz.click_Product3();
  shopAmz.windowHandling();
  shopAmz.click_AddToCart_Type2();
  shopAmz.close_CurrentWindow();
  shopAmz.switch_ToParentWindow();
  shopAmz.click_Product4();
  shopAmz.windowHandling();
  shopAmz.click_AddToCart_Type2();
   cartAmz.click_CartLink();
   cartAmz.click_SaveForLater();
  }
  @Test(priority=18)
  public void deleteItemFromCart_Verification()
  {homePG.launchURL();
   shopAmz.click_Product2();
   shopAmz.windowHandling();
   shopAmz.click_AddToCart();
   shopAmz.click_XButton();
   cartAmz.click_CartLink();
   cartAmz.click_DeleteButton();
  }
  @Test(priority=19)
  public void proceedItemstoCheckOut_Verification()
  {homePG.launchURL();
   shopAmz.click_Product2();
   shopAmz.windowHandling();
   shopAmz.click_AddToCart();
   shopAmz.click_XButton();
   cartAmz.click_CartLink();
   cartAmz.click_ProceedToCheckout();
  }
  @Test(priority=20)
  public void productdetails_Verification() throws InterruptedException
  { homePG.launchURL();
    homePG.click_PlayStation();
    shopAmz.windowHandling();
    homePG.check_Playstationdetails();
  }
  @Test(priority=21)
  public void addProductToWishlistS_Verification()
  {homePG.launchURL();
   homePG.click_PlayStation();
   shopAmz.windowHandling();
  // homePG.check_Playstationdetails();
   shopAmz.click_AddToWishList();
  }
  @Test(priority=22)
  public void buyNowButtonfunctionality_Verification()
  {homePG.launchURL();
   homePG.verify_GlobalSearchBox();
   homePG.verify_SearchIcon();
   shopAmz.clickOn_Product1();
   shopAmz.windowHandling();
   shopAmz.click_BuyNowButton();
  }
 
  
  
}
