package testWebA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Automate_Components {
	@Test
	public void method1() throws InterruptedException {
		System.out.println("***First method test***");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sheka\\Downloads\\selen\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String url ="https://automationstepbystep.com/";
		driver.get(url);
		Thread.sleep(5000);
		driver.manage().window().maximize();
		System.out.println("Title of the website "+driver.getTitle());
		System.out.println("My Job is done , Good Bye");
		driver.close();
		
	}
	

}
