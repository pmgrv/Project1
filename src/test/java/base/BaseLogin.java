package base;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class BaseLogin {
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(BaseLogin.class.getName());

	
	@BeforeMethod
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pk\\eclipse-workspace\\MachineTest_2021\\Project1\\src\\test\\java\\webdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
//	@Test(priority=1)
	public static void LoginAt() {
		driver.navigate().to("http://automationpractice.com/");
		WebElement loginButton = driver.findElement(By.xpath("//a[@class='login']"));
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement password = driver.findElement(By.id("passwd"));
		username.clear();
		password.clear();
		
		username.sendKeys("test143444@gmail.com");
		password.sendKeys("Pmgrv143");
		WebElement loginFinal = driver.findElement(By.id("SubmitLogin"));
		loginFinal.click();
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Order history and details")));
		Assert.assertEquals(driver.getTitle(), "My account - My Store");
		
		
	}
	@Test(priority=2)
	public static void AddInCart() {
		BaseLogin.LoginAt();
		Assert.assertEquals("My account - My Store", driver.getTitle());
		Actions action = new Actions(driver);
		WebElement womenSection = driver.findElement(By.xpath("//a[@title='Women']"));
//		womenSection.click();
		action.moveToElement(womenSection).perform();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement blousesSection = driver.findElement(By.xpath("//li[@class='last']/a"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		blousesSection.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Get title " + driver.getTitle() + " <<<<<< ");
		List<WebElement> getToCart = driver.findElements(By.xpath("//a[@title='Add to cart']"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Count of Items " + getToCart.size());
		for(int i = 0 ; i < getToCart.size()-5; i++) {
			getToCart=driver.findElements(By.xpath("//a[@title='Add to cart']"));
			getToCart.add(blousesSection);
			getToCart.get(i).click();

			if(getToCart.size()==2) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Proceed to checkout']")));
				WebElement proceedToCheckout= driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
				proceedToCheckout.click();
			}
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Continue shopping']")));
			WebElement clickToContinue = driver.findElement(By.xpath("//span[@title='Continue shopping']"));
			clickToContinue.click();
//			driver.navigate().back();
		}
		
		System.out.println("Count of Items " + getToCart.size());
//		getToCart.click();
//		WebElement proceedToCheckout= driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
//		proceedToCheckout.click();
//		getToCart.click();
		
	}
	
	@Test(priority=3)
	public static void LogOut(){
		BaseLogin.LoginAt();
		
	}
	@AfterMethod
	public void TearUp() {
		driver.quit();
	}
}
