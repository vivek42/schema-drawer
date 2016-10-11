package com.crawler.schema.integration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GuestFeatureTest {
	
	
	@Test
	public void testFirst() throws InterruptedException {
		
//		WebDriver driver = new ChromeDriver();
//		  driver.get("http://localhost");
//		  Thread.sleep(5000);  // Let the user actually see something!
//		  WebElement searchBox = driver.findElement(By.name("q"));
//		  searchBox.sendKeys("ChromeDriver");
//		  searchBox.submit();
//		  Thread.sleep(5000);  // Let the user actually see something!
//		  driver.quit();
		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();
		
        //Launch the Online Store Website
		driver.get("http://localhost:30018/ui/login");
 
        // Print a Log In message to the screen
        System.out.println("Successfully opened the website www.Store.Demoqa.com");
 
		//Wait for 5 Sec
		Thread.sleep(5);
		
        // Close the driver
        driver.quit();
	}
}
