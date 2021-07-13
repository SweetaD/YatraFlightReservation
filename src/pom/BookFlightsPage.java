package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookFlightsPage {
	WebDriver driver;
	
    By departFrom=By.xpath("//input[@id='BE_flight_origin_city']");
	By goingTo=By.id("BE_flight_arrival_city");
	By city=By.xpath("//li[@class=\"active ac_over\"]");
	By departureDate=By.id("BE_flight_origin_date");
	By travellers=By.xpath("//span[@class='txt-ellipses flight_passengerBox travellerPaxBox']");
	By removeAdults=By.xpath("//*[@id=\"BE_flight_passengerBox\"]/div[1]/div[1]/div/div/span[1]");
	By addAdults=By.xpath("//*[@id=\"BE_flight_passengerBox\"]/div[1]/div[1]/div/div/span[2]");
	By removeChild=By.xpath("//*[@id=\"BE_flight_passengerBox\"]/div[1]/div[2]/div/div/span[1]");
	By addChild=By.xpath("//*[@id=\"BE_flight_passengerBox\"]/div[1]/div[2]/div/div/span[2]");
	By removeInfant=By.xpath("//*[@id=\"BE_flight_passengerBox\"]/div[1]/div[3]/div/div/span[1]");
	By addInfant=By.xpath("//*[@id=\"BE_flight_passengerBox\"]/div[1]/div[3]/div/div/span[2]");
	By economy=By.xpath("//*[@id=\"flight_class_select_child\"]/ul/li[1]");
	By premiumEconomy=By.xpath("//*[@id=\"flight_class_select_child\"]/ul/li[2]");
	By business=By.xpath("//*[@id=\"flight_class_select_child\"]/ul/li[3]");
	By nonStop=By.xpath("//*[@id=\"BE_flight_form_wrapper\"]/div[3]/div[1]/div/a");
	By searchButton=By.xpath("//*[@id=\"BE_flight_flsearch_btn\"]");
			
	public BookFlightsPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void selectDepartFrom(String arg) throws InterruptedException {
		driver.findElement(departFrom).click();
		Thread.sleep(1000);
		driver.findElement(departFrom).sendKeys(arg);
	    Thread.sleep(1000);
	    driver.findElement(city).click();
	    Thread.sleep(1000);

		
	}
	public void selectGoingTo(String arg) throws InterruptedException {
		driver.findElement(goingTo).click();
		Thread.sleep(1000);
		driver.findElement(goingTo).sendKeys(arg);
	    Thread.sleep(1000);
	    driver.findElement(city).click();
	    Thread.sleep(1000);
	}
	public void selectDepartureDate(String arg) throws InterruptedException {
		
		
		driver.findElement(departureDate).click();
		Thread.sleep(1000);
		WebElement ele = driver.findElement(By.id(arg));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
		
		
	}
public void selectTravellers(String adult, String child, String infant, String seatType) throws InterruptedException {
		
		int adt=Integer.parseInt(adult);
		int chi=Integer.parseInt(child);
		int inf=Integer.parseInt(infant);
		driver.findElement(travellers).click();
		Thread.sleep(1000);
		while(adt!=1) {
			driver.findElement(addAdults).click();
			adt--;
			Thread.sleep(1000);
		}
		while(chi!=0) {
			driver.findElement(addChild).click();
			chi--;
			Thread.sleep(1000);
		}
		while(inf!=0) {
			driver.findElement(addInfant).click();
			inf--;
			Thread.sleep(1000);
		}
	    if(seatType.equalsIgnoreCase("Premium Economy")) {
			driver.findElement(premiumEconomy).click();
			Thread.sleep(1000);
		}
		else if(seatType.equalsIgnoreCase("Business")) {
			driver.findElement(business).click();
			Thread.sleep(1000);
		}
		else{
			driver.findElement(economy).click();
			Thread.sleep(1000);
		} 
	    driver.findElement(travellers).click();
	    Thread.sleep(1000);
		
	}
public void selectNonStop(String arg) throws InterruptedException {
	
	if(arg.equalsIgnoreCase("yes")) {
		
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"BE_flight_form_wrapper\"]/div[3]/div[1]/div/a"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
		Thread.sleep(1000);
	}
	
}
public void clickSearchButton() throws InterruptedException {
	
	
		driver.findElement(searchButton).click();
}
public void searchFlight(String from, String to, String date, String adt, String chi, String inf, String st, String yn) throws InterruptedException {
	selectDepartFrom(from);
	selectGoingTo(to);
	selectDepartureDate(date);
	selectTravellers(adt, chi, inf, st );
	selectNonStop(yn);
	clickSearchButton();
		
}

	
}
