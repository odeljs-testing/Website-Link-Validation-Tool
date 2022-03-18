package linkValidation;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ListBuilder {

	public static LinkList urlList = new LinkList();
	
	static LinkedList<String> URLList = new LinkedList<>();
	
	private static WebDriver driver;
	
	
	public ListBuilder(String domain){
		
		System.setProperty("webdriver.chrome.driver","/Users/odelj/Selenium/chromedriver");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
	
		URLList.add(domain);
		}
	
	
	//add all urls with WHOLEHEALTHLIBRARY as part of location
	public void buildWebpageList() {
	
	
		
		try {
		for(int k = 0; k < URLList.size(); k++) {
			//create driver + webpage
			
			driver.get(URLList.get(k));
			
			//get all href elements on the driver webpage
			List<WebElement> links = driver.findElements(By.tagName("a"));
			
			//iterate through all links on page
			for(int i = 0; i < links.size(); i++) {
				
				
				try {
				//get the href element
				WebElement currentElement = links.get(i);
				
				//create string of href element to pass to verifier method
				String currentHref = currentElement.getAttribute("href");
				
				//if null skip iteration
				if(currentHref == null || currentHref.contains("#")) {
					continue;
				}
					//if currentHref is http and has WHOLEHEALTHLIBRARY
					if(currentHref.contains("http") && currentHref.contains("WHOLEHEALTHLIBRARY")) {
						
					
						//if current URL is .asp and not already in URLList add to URLList
					if(currentHref.contains(".asp") && !URLList.contains(currentHref)) {
						URLList.add(currentHref);
						
					}
					}
					
				
				
					
				}catch(StaleElementReferenceException e) {
					System.out.println("Caught error on page: " + URLList.get(k));
				}
				


			}

		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	 
	
	//search for desired url from URLList
	 public void SearchForUrl(String url) {
		 
		 try {
				for(int k = 0; k < URLList.size(); k++) {
					//create driver + webpage
					
					driver.get(URLList.get(k));
					
					//get all href elements on the driver webpage
					List<WebElement> links = driver.findElements(By.tagName("a"));
					
					//iterate through all links on page
					for(int i = 0; i < links.size(); i++) {
						
						
						try {
						//get the href element
						WebElement currentElement = links.get(i);
						
						//create string of href element to pass to verifier method
						String currentHref = currentElement.getAttribute("href");
						
						//if null skip iteration
						if(currentHref == null || currentHref.contains("#")) {
							continue;
						}
						
						if(currentHref.contains(url)) {
							System.out.println("Link: " + url + "Found at: " + URLList.get(k));
							}
						
						}catch(StaleElementReferenceException e) {
						System.out.println("Caught error on page: " + URLList.get(k));
					}
					


				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 
	}
	


