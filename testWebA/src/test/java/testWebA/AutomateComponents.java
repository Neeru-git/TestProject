package testWebA;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomateComponents {
	WebDriver driver;
	String url = "https://rahulshettyacademy.com/AutomationPractice/";

	@BeforeTest(enabled = true)
	public void startDriver() throws InterruptedException {
		System.out.println("***First method test***");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sheka\\Downloads\\selen\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		Thread.sleep(2000);
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

	@Test(enabled = true)
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
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
		
	}
	public void tableData() {
		////table[@id='product']/tbody/tr
	}

	@AfterTest(enabled = true)
	public void closeMethod() {
		driver.quit();
	}
}
