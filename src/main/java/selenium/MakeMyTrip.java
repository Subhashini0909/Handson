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
	
	String formatted_Dept_date;
	public void DeptDate() throws InterruptedException
	{
		driver.findElement(By.className("DayPicker-Months"));
		
//Getting user input
		Scanner from_input = new Scanner(System.in);
		System.out.print("Enter departure date in EEE MMM dd yyyy format : ");
		String Dept_date = from_input.nextLine();
		DateTimeFormatter Dept_date_format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		formatted_Dept_date = Dept_date.formatted(Dept_date_format);
		
	
//Format Today's date(only day part)
		LocalDate Today_Date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		String formatted = Today_Date.format(format);
		
		if(formatted.compareTo(formatted_Dept_date)<=0)
		{
			System.err.println("Enter date from the current date");
		}
		else
		{
			driver.findElement(By.xpath(String.format("//div[contains(@aria-label,'%s')]", formatted_Dept_date))).click();
		}
	}
	
	public void ReturnDate()
		{
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter return date in EEE MMM dd yyyy format : ");
		String return_date = sc1.nextLine();
		DateTimeFormatter return_date_format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		String formatter_return_date = return_date.formatted(return_date_format);
		
		driver.findElement(By.xpath("//div[@data-cy = 'returnArea']")).click();
		if(formatted_Dept_date.compareTo(formatter_return_date)<=0)
			{
			driver.findElement(By.xpath(String.format("//div[contains(@aria-label,'%s')]", formatter_return_date))).click();
			}
		else
			{
			System.err.println("Enter date should be greater than the current date");
			}
		
		}
	
	public void search()
	{
		driver.findElement(By.partialLinkText("Search")).click();
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
		//trip.quit();
	}
}
