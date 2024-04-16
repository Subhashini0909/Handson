package selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScreenshotAlignment {
	
	ChromeDriver driver = new ChromeDriver();
	
	public static void main(String[] args)
	{
		
		
	}
	public void launch()
	{
		
		driver.get("https://www.geeksforgeeks.org/");
	}
	
	public void screenshot() throws IOException
	{
		String loc = "C:\\Users\\worth\\Desktop\\Proof_Test\\";
		
		for(int i=1;i<=5; i++)
		{
		File GG = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File(loc+i +".png");
		org.openqa.selenium.io.FileHandler.copy(GG, dest);
		}
	}
	
	String File_Name = "GeeksForGeeks_Login.docx";
	public void Screenshot_To_Word() throws FileNotFoundException
	{
		XWPFDocument doc = new XWPFDocument();
		FileOutputStream output = new FileOutputStream(File_Name);
		
		File Folder = new File("Proof_Test");
		File[] screenshot_file = Folder.listFiles();
	}
	
	public void quit()
	{
		driver.quit();
	}

}
