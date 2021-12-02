package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class SwagLabs {

	public static WebDriver driver;
	public static String invNAME="";  
	public static String invPRICE ;
	@BeforeMethod
	public static void SetUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pk\\eclipse-workspace\\MachineTest_2021\\Project1\\src\\test\\java\\webdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	} 
	
	@Test
	public static void Login() {	
		driver.navigate().to("https://www.saucedemo.com/");		
		System.out.println("Get link " + driver.getTitle() + "<<<");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		WebElement getUsername = driver.findElement(By.xpath("//*[@id='login_credentials']/text()[3]"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		WebElement getPassword = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/div[2]/div/div[2]/text()"));
//		String uname = getUsername.getText();
//		String pswrd = getPassword.getText();
//		System.out.println("UNAME "+ uname + "\n PWRD " + pswrd);
		
		WebElement uname = driver.findElement(By.id("user-name"));
		WebElement pswrd = driver.findElement(By.id("password"));
		uname.clear();
		pswrd.clear();
		uname.sendKeys("standard_user");
		pswrd.sendKeys("secret_sauce");
		
		WebElement loginToPlatform = driver.findElement(By.xpath("//input[@id='login-button']"));
		loginToPlatform.click();
		System.out.println("Get title and link " + driver.getTitle() + "\n URL " + driver.getCurrentUrl());
		
	}
	
	@Test
	public static void GetCart() {
		SwagLabs.Login();
		WebElement itemToCart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
		itemToCart.click();
		WebElement itemCountInCart = driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a"));
		System.out.println("Items in the Cart " + itemCountInCart.getText());
		itemCountInCart.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement invPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
		WebElement invName= driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
		invNAME  = invName.getText();
		invPRICE = invPrice.getText();
		WebElement checkOutCart = driver.findElement(By.id("checkout"));
		checkOutCart.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Enter user details  
		WebElement firstName = driver.findElement(By.id("first-name"));
		WebElement lastName = driver.findElement(By.id("last-name"));
		WebElement postalCode = driver.findElement(By.id("postal-code"));
		firstName.clear();
		lastName.clear();
		postalCode.clear();
		
		firstName.sendKeys("Pravinkumar");
		lastName.sendKeys("R");
		postalCode.sendKeys("441901");
		
		WebElement continueToSell = driver.findElement(By.id("continue"));
		continueToSell.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	@Test
	public static void VerifyCart() {
		SwagLabs.GetCart();
		WebElement invPriceV = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
		WebElement invNameV= driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
		String invNAMEV  = invNameV.getText();
		String invPRICEV = invPriceV.getText();
		Assert.assertEquals(invNAMEV, invNAME);
		Assert.assertEquals(invPRICEV, invPRICE);
		System.out.println("In VerifyCart: \nPRice : " + invPRICEV + "\nNAME: " + invNAMEV);
		//WebElement finishCart = driver.findElement(null);
	}
	
	@Test
	public static void VerifyCartAutoExecute() {
		SwagLabs.GetCart();
		WebElement invPriceV = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
		WebElement invNameV= driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
		String invNAMEV  = invNameV.getText();
		String invPRICEV = invPriceV.getText();
		Assert.assertEquals(invNAMEV, invNAME);
		Assert.assertEquals(invPRICEV, invPRICE);
		System.out.println("In VerifyCart: \nPRice : " + invPRICEV + "\nNAME: " + invNAMEV);
		//WebElement finishCart = driver.findElement(null);
	}
	
	@AfterMethod
	public static void TearDown() {
		driver.quit();
	}
}

