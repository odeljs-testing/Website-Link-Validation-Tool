package linkValidation;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HrefValidation {
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//boolean checker,true = active false = not active
		boolean activeChecker = true;
		
		//list that holds urls to be checked
		LinkedList<String> URLList = new LinkedList<>();
		
		System.setProperty("webdriver.chrome.driver","/Users/odelj/Selenium/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		
		Date date = Calendar.getInstance().getTime();  
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		
		String currentDate = dateFormat.format(date);  
		
		//CSV file
		File csvFile = new File(currentDate + "-ActiveLinkReport.csv");
		
		PrintWriter out = new PrintWriter(csvFile);
		
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
		
			
			
			//iterate through all links on page
			for(int i = 0; i < links.size(); i++) {
				
				//get the first href element
				WebElement currentElement = links.get(i);
				
				//create string of href element to pass to verifier method
				String currentHref = currentElement.getAttribute("href");
			
				//if null skip iteration
				if(currentHref == null) {
					continue;
				}
				
				//if current URL contains http then verify
				if(currentHref.contains("http")) {
					
					//call method to verify currentURL
					activeChecker = verifyLinkIsActive(currentHref);
				}
				
				//if activeLinkChecker is false it is a bad link so input it and the webpage its
				//on into csv file
				
				if(activeChecker == false) {
					out.printf("%s, %s", URLList.getFirst(), currentHref);
				}
				
			} 
		
			//remove the already checked URL webpage from the list
			URLList.removeFirst();
			
			//remove all items from href list
			links.clear();
			
		
		
		}
	
	out.close();
	
		
	
	}
	
	
	public static boolean verifyLinkIsActive(String currentHref)  {
		
		boolean activeChecker = true;
		
		  try 
	        {
	           URL url = new URL(currentHref);
	           
	           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	           
	           httpURLConnect.setConnectTimeout(3000);
	           
	           httpURLConnect.connect();
	           
	           if(httpURLConnect.getResponseCode()==200)
	           {
	               activeChecker = true;
	        	   System.out.println(currentHref+" - "+httpURLConnect.getResponseMessage());
	            }
	          if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
	           {
	              activeChecker = false; 
	        	  System.out.println(currentHref+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
	            }
	        } catch (Exception e) {
	           
	        }
		  return activeChecker;
	}
}
