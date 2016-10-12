package com.crawler.schema.integration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GuestFeatureTest {
	
	@Test
	public void testGuestDownload() throws InterruptedException {
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://localhost:8080/ui/login");
		driver.findElement(By.id("guestLink")).click();
		driver.findElement(By.id("uploadContentFile")).sendKeys("/Users/vivekchouhan/Desktop/testUpload.txt");
		Thread.sleep(5000);
		driver.findElement(By.id("generateSchema")).click();
        // Print a Log In message to the screen
        System.out.println("Successfully opened guest home");
 
		//Wait for 5 Sec
		Thread.sleep(5000);
		
        // Close the driver
        driver.quit();
	}
}
