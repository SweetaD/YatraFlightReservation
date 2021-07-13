package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pom.BookFlightsPage;
import utility.ReadExcelFile;


public class FlightReservation {
	WebDriver driver;
	BookFlightsPage bfp;

@BeforeTest
public void setUp()  throws AWTException, InterruptedException
 {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\91948\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		String url="https://www.yatra.com";
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Assert.assertEquals(driver.getCurrentUrl(), url);
		bfp=new BookFlightsPage(driver);
		
		
}
@Test(dataProvider="testdata")
public void Test1(String from, String to, String date, String adult, String child, String infant,String seatType, String nonStop) throws InterruptedException {
	bfp.searchFlight( from, to, date, adult, child, infant, seatType, nonStop);
	Thread.sleep(3000);
	Assert.assertEquals("Yatra.com | Bengaluru to Delhi flights", driver.getTitle());
	
}

@AfterTest
public void tearDown() {
	driver.close();
}
@DataProvider(name="testdata")
public String[][] testDataExample() throws IOException{
	System.out.print("hello");
ReadExcelFile configuration = new ReadExcelFile(".\\datafile\\data.xlsx");
int totalrows=configuration.getRowCount("Sheet1");
int totalcols=configuration.getCellCount("Sheet1", 1);
String[][]signin_credentials = new String[totalrows][totalcols];

for(int i=1;i<=totalrows;i++)
{
	for(int j=0;j<totalcols;j++) {
		signin_credentials[i-1][j]=configuration.getCellData("Sheet1", i, j);
	}
}
return signin_credentials;
}

}
