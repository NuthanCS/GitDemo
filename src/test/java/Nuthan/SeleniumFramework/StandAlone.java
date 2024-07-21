package Nuthan.SeleniumFramework;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		
		String itemName = "ZARA COAT 3";
		String countryName = "ind";
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\Admin\\\\Documents\\\\drivers\\\\chromedriver-win64\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("raj01@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Welcome123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		//get all the elements and traverse each element and fetch the element matching with the given string name
		List<WebElement> items = driver.findElements(By.xpath("//div[@class='card-body']"));
		WebElement ele = items.stream().filter(item->
		item.findElement(By.xpath("//div[@class='card-body']/h5/b")).getText().equalsIgnoreCase(itemName)).findFirst().orElse(null);
//		System.out.println(ele);
		ele.findElement(By.xpath("//*[text()='ZARA COAT 3']/ancestor::div[@class='card']//button[text()=' Add To Cart']")).click();
		
		//wait for the success toast message to appear and loading screen to disappear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//traverse through each element in the cart page and assert the desired item is displayed or not
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='infoWrap']"));	
		Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(itemName));
//		Assert.assertTrue(match);
		
		//click on the checkout button
		driver.findElement(By.xpath("//button[.='Checkout']")).click();

        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(countryName);
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[normalize-space(text())='India']/ancestor::button")));
//  
//		driver.findElement(By.xpath(".//*[normalize-space(text())='India']/ancestor::button/span")).click();
//		driver.findElement(By.cssSelector(".action__submit")).click();
//		
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));



        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();



        WebElement e = driver.findElement(By.cssSelector(".btnn"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click(0);", e);



        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		

	}
}
