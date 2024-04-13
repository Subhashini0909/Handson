package selenium;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.poi.ss.formula.functions.Today;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {
	
	ChromeDriver driver = new ChromeDriver();
	
	public void LaunchBrowser() throws IOException
	{
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		
		File scr = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Proofs/img1.png");
		FileHandler.copy(scr, dest);
	}
	
	public void from()
	{
		driver.findElement(By.id("fromCity")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("Bengaluru");
		driver.findElement(By.xpath("//p[text()='Bengaluru International Airport']")).click();
	}
	
	public void to()
	{
		driver.findElement(By.id("toCity")).click();
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("Chennai");
		driver.findElement(By.id("react-autowhatever-1-section-0-item-0")).click();
		
	}
	
LocalDate deptDate;
	
	public void DeptDate() throws InterruptedException {
		driver.findElement(By.className("DayPicker-Months"));

//USER INPUT
		System.out.print("Enter departure date in Mon Apr 15 2024 format : ");
		Scanner sc_dept = new Scanner(System.in);
		String Dept_date = sc_dept.nextLine();

//Formatting(EEE MMM dd yyyy) and parsing 
		DateTimeFormatter DeptDate_format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		 deptDate = LocalDate.parse(Dept_date, DeptDate_format);
		 	//System.out.println("User entered date : " +deptDate);
	
//Formatted as per the format in DeptDate_format(EEE MMM dd yyyy) 
		 String formatted_Dept_date = Dept_date.formatted(DeptDate_format);
		 	//System.out.println(formatted_Dept_date);
		
//Today's Date		
		LocalDate Today_Date = LocalDate.now();
			//System.out.println("Today's date : " +Today_Date);
		
		if(deptDate.isAfter(Today_Date))
		{
			driver.findElement(By.xpath(String.format("//div[contains(@aria-label,'%s')]", formatted_Dept_date))).click();
		}
		else
		{
			System.err.println("Enter date from the current date");
		}
	}

//	   
	// ReturnDate method
	public void ReturnDate() 
	{
//User Input
		System.out.print("Enter return date in Mon Apr 15 2024 format : ");
		Scanner sc_return = new Scanner(System.in);
		String return_date = sc_return.nextLine();

//Formatting(EEE MMM dd yyyy) and parsing 
		DateTimeFormatter ReturnDate_format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		LocalDate ReturnDate = LocalDate.parse(return_date, ReturnDate_format);
			//System.out.println(ReturnDate);
		String formatted_Return_date = return_date.formatted(ReturnDate_format);
		
		driver.findElement(By.xpath("//div[@data-cy = 'returnArea']")).click();
		
		if(ReturnDate.isBefore(deptDate))
		{
			System.err.println("Invalid return date");
		}
		else
		{
			driver.findElement(By.xpath(String.format("//div[contains(@aria-label,'%s')]", formatted_Return_date))).click();
		}
	}
	
	public void search() throws InterruptedException
	{
		driver.findElement(By.xpath("//p//a[text()='Search']")).click();
		Thread.sleep(5000);
	}
	
	public void navigated() //Currently when navigated it's getting network issue
	{
		WebElement LockPrice_Overlay = driver.findElement(By.className("commonOverlay "));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(LockPrice_Overlay));
	}
	public void quit()
	{
		driver.quit();
	}
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		MakeMyTrip trip = new MakeMyTrip();
		trip.LaunchBrowser();
		trip.from();
		trip.to();
		trip.DeptDate();
		trip.ReturnDate();
		trip.search();
		//trip.navigated();
		trip.quit();
	}
}
