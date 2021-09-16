package linkValidation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HrefValidation {
	
	
	
	public static void main(String[] args) {
		
		//list that holds urls to be checked
		LinkedList<String> URLList = new LinkedList<>();
		
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		//Get URLs to pages from file
		try {
			
			File myFile = new File("WholeHealthSitePages.txt");
			
			Scanner myReader = new Scanner(myFile);
			
			while(myReader.hasNextLine()) {
				
				String currentURL = myReader.nextLine();
				
				URLList.add(currentURL);
				
			}
			
		}
		catch(FileNotFoundException e){
			
			System.out.println("An error occurred while reading the website list file");
			
			e.printStackTrace();
		}
	
		
		//while there is a webpage URL in the list that we want to check
		while(URLList.size() != 0 && URLList.size() > 0) {
			
			//create driver + webpage
			driver.get(URLList.getFirst());
			
			//get all href elements on the driver webpage
			List<WebElement> links = driver.findElements(By.tagName("a"));
		}
	
	
		
	
	}
}
