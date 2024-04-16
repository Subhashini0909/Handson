package selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GitHub_Contribution_Count {

	public static void main(String[] args) 
	{
		
		String URL = "https://github.com/Subhashini0909";
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		
	//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> contri= driver.findElements(By.xpath("//td[@aria-describedby='contribution-graph-legend-level-4']"));
	
	//Explicit Wait
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfAllElements(contri));
		
	
		System.out.println("You have contributed on the following date's in 2024 : ");
		
		for(WebElement Contribution : contri)
		{
			
			String c_2024 = Contribution.getAttribute("data-date");
			//System.out.println(c_2024);
		}
		
		int Total_Contriubtions = contri.size();
		System.out.println('\n' + "Your Total Contribution : "+ Total_Contriubtions);
		
		
		driver.quit();

	}

}
