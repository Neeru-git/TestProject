package testWebA;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomateComponents {
	WebDriver driver;
	String url = "https://rahulshettyacademy.com/AutomationPractice/";
	String url1="https://www.testandquiz.com/selenium/testing.html";

	@BeforeTest(enabled = true)
	public void startDriver() throws InterruptedException {
		System.out.println("***Starting the web driver***");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sheka\\Downloads\\selen\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		//Thread.sleep(500);
		driver.manage().window().maximize();
		System.out.println("Title of the website :" + driver.getTitle());
		System.out.println("My Job is done, your browser is open");
	}

	@Test(enabled = false)
	public void basicComponents() throws InterruptedException {
		System.out.println("First window handle " + driver.getWindowHandle());
		driver.findElement(By.xpath("//input[@value='radio2']")).click();
		driver.findElement(By.id("autocomplete")).sendKeys("India");
		/*
		 * DropDown another way
		 * //driver.findElement(By.xpath("//select[@name='dropdown-class-example']")).
		 * click();
		 * //driver.findElement(By.xpath("//option[@value='option3']")).click();
		 */
		// Selecting from a dropdown using select class
		Select see = new Select(driver.findElement(By.xpath("//select[@name='dropdown-class-example']")));
		see.selectByValue("option3");
		driver.findElement(By.xpath("//input[@id='checkBoxOption2']")).click();
	}

	@Test(enabled = false)
	public void newWindowHandling() throws InterruptedException {
		driver.findElement(By.xpath("//button[@id='openwindow']")).click();
		Thread.sleep(1000);
		Set<String> handles = driver.getWindowHandles();
		System.out.println("Available handles " + handles);
		// driver.switchTo().window("http://www.qaclickacademy.com/");
		String HomeWindow = driver.getWindowHandle();
		System.out.println("First window title is :" + driver.getTitle());
		for (String handle1 : handles) {
			// System.out.println("Handle1 print is " + handle1);
			System.out.println("Last print " + driver.switchTo().window(handle1).getCurrentUrl());
			// driver.switchTo().
		}
		// here we can perform operations on second window
		System.out.println("It's the second window " + driver.getCurrentUrl());
		// Closing Pop Up window
		driver.close();
		driver.switchTo().window(HomeWindow);
	}

	@Test(enabled = false)
	public void newTabHandling() {
		driver.findElement(By.xpath("//a[@id='opentab']")).click();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs.get(0) + " ," + tabs.get(1));
		System.out.println("Old  tab url is " + driver.switchTo().window(tabs.get(0)).getCurrentUrl());
		System.out.println("New tab url is " + driver.switchTo().window(tabs.get(1)).getCurrentUrl());
		driver.quit();
	}

	@Test(enabled = false)
	public void alertHandling() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Neeru");
		driver.findElement(By.xpath("//input[@id='alertbtn']")).click();
		//// input[@id='confirmbtn']
		String CaptureAlert=driver.switchTo().alert().getText();
		
		assertEquals(CaptureAlert, "Hello Neeru, share this practice page and share your knowledge", "Text not Matched--");
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Neeru");
		driver.findElement(By.xpath("// input[@id='confirmbtn']")).click();
		System.out.println("Confirm alert text "+driver.switchTo().alert().getText());
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
		
	}
	@Test(enabled=true)
	public void tableData() {
		////table[@id='product']/tbody/tr
		
		WebElement myElement = driver.findElement(By.xpath("//table[@id='product']/tbody"));
		System.out.println(myElement.getText());
		List<WebElement> myRows=myElement.findElements(By.xpath("//table[@id='product']/tbody/tr"));
		int noOfRows=myRows.size();
		System.out.println("No.of rows in the table "+noOfRows);
		
	}
	@Test(enabled=false)
	public void elementDisplayedEx() {
		
		//WebElement showButton=driver.findElement(By.xpath("//input[@id='show-textbox']"));
		System.out.println("Entering the method");
		WebElement visibleField=driver.findElement(By.xpath("//input[@id='displayed-text']"));
		
		if(visibleField.isDisplayed()) {
			driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();
		}
		else {
			System.out.println("Already hidden");
		}
		
	}
	@Test(enabled=true)
	public void mouseHover() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		//Thread.sleep(500);
		Actions act1= new Actions(driver);
		WebElement mouseMove = driver.findElement(By.xpath("//button[@id='mousehover']"));
		act1.moveToElement(mouseMove).perform();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Top']")).click();		
		
	}
	
	
	@Test(enabled=false)
	
	public void dragAndDrop() throws InterruptedException {
		//url-https://www.testandquiz.com/selenium/testing.html
		WebElement from =driver.findElement(By.xpath("//img[@id='sourceImage']"));
		WebElement to = driver.findElement(By.xpath("//div[@id='targetDiv']"));  
		Actions act = new Actions(driver);
		//act.clickAndHold(from).moveByOffset(-1, -1).clickAndHold(to).release(to).build().perform();
		act.dragAndDrop(from, to).release().perform();
		//Thread.sleep(500);
		System.out.println("Drag and drop done!");
		// THIS CODE IS NT WORKING for me
	}

	@AfterTest(enabled = true)
	public void closeMethod() {
		driver.quit();
	}
}
