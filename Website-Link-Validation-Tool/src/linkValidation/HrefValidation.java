package linkValidation;


import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;








public class HrefValidation {
	
	//list that holds urls to be checked
			static LinkedList<String> URLList = new LinkedList<>();
			static LinkedList<String> PDFList = new LinkedList<>();
			
			
			
			
	public static void main(String[] args) throws IOException {
		
	
		System.setProperty("webdriver.chrome.driver","/Users/odelj/Selenium/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		
		
		//Get starting URL from command line
		URLList.add(args[0]);
		
		buildList(driver);
		
		pdfValidation();
						
	}
	
	
	//Sort and build list to validate
	public static void buildList(WebDriver driver) throws IOException{
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
	    String currentDate = formatter.format(date);    
		
		//CSV file
		File urlFile = new File(currentDate + "-ActiveLinkReport.csv");
		
		PrintWriter out = new PrintWriter(urlFile);
		
		//title rows of csv
		out.println("Webpage,Href,Validation\n");
		
		//CSV file
		File pdfFile = new File(currentDate + "-ActivePDFReport.csv");
				
		PrintWriter outTwo = new PrintWriter(pdfFile);
				
				//title rows of csv
		outTwo.println("Webpage,PDF,Validation\n");
		
		boolean activeChecker;
		 
		
			
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
				
				//if current URL contains http then verify
				if(currentHref.contains("http") && currentHref.contains("WHOLEHEALTHLIBRARY")) {
					
				
				//if current URL is .asp do link check
				if(currentHref.contains(".asp") && !URLList.contains(currentHref)) {
					URLList.add(currentHref);
					//call method to verify currentURL
					activeChecker = verifyLinkIsActive(currentHref);
					
					

					
					if(activeChecker == false) {
						
						out.println(URLList.get(k) + "," + currentHref + ","
						+ "invalid");
						
					}else {
						
						out.println(URLList.get(k) + "," + currentHref + "," 
						+ "valid");
						
					}
					
					out.flush();
					
				}
				
				
				
				//if current URL is pdf do link check
				if(currentHref.contains(".pdf") && !PDFList.contains(currentHref)) {
					
					
					activeChecker = verifyLinkIsActive(currentHref);
					
					
					
					if(activeChecker == false) {
						
						outTwo.println(URLList.get(k) + "," + currentHref + ","
						+ "invalid");
						
					}else {
						PDFList.add(currentHref);
						outTwo.println(URLList.get(k) + "," + currentHref + "," 
						+ "valid");
						
					}
						}
					
					outTwo.flush();
					}
					
					
				}catch(StaleElementReferenceException e) {
					System.out.println("Caught error");
				}
				
				
			}
		links.clear();
		
		}
		
		outTwo.close();
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
	        	   
	            }
	          if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
	           {
	              activeChecker = false; 
	        	 
	            }
	        } catch (Exception e) {
	           
	        }
		  return activeChecker;
	}
	
	public static void pdfValidation() throws IOException {
		
		PDFTextStripper tStripper = new PDFTextStripper();
		tStripper.setStartPage(1);
		tStripper.setEndPage(3);
		String content = null;
		String urls = null;
		
		boolean activeChecker = false;
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
	    String currentDate = formatter.format(date);    
		
		//CSV file
		File urlFile = new File(currentDate + "-ExternalPDFLinkReport.csv");
		
		PrintWriter out = new PrintWriter(urlFile);
		
		//title rows of csv
		out.println("PDF,Href,Validation\n");
		
		for(int i = 0; i < PDFList.size(); i++) {
		
		//need to load doc from URL
		
			InputStream is = new URL(PDFList.get(i)).openStream();
			PDDocument document = PDDocument.load(is);
		
		document.getClass();
		
		
		
		if(!document.isEncrypted()) {
			String pdfFileInText = tStripper.getText(document);
			String[] lines = pdfFileInText.split("\\r\\n\\r\\n");
			
			for (String line : lines) {
               // System.out.println(line);
                content += line;
			}
		}
		
		
		for(int k = 0; k < document.getNumberOfPages(); k++) {
		
		PDPage pdfpage = document.getPage(k);
        List<PDAnnotation> annotations = pdfpage.getAnnotations();
        for (int j = 0; j < annotations.size(); j++) {
            PDAnnotation annot = annotations.get(j);
            
            if (annot instanceof PDAnnotationLink) {
                PDAnnotationLink link = (PDAnnotationLink) annot;
                PDAction action = link.getAction();
                
                if (action instanceof PDActionURI) {
                  
                	PDActionURI uri = (PDActionURI) action;
                    urls += uri.getURI();
                    
                    activeChecker = verifyLinkIsActive(uri.getURI());
                    
                    if(activeChecker == false) {
                    	out.println(PDFList.get(i) + "," + uri.getURI() + "," 
                				+ "invalid");
                				
                    }
                    
                }
            }
        
            
        }
        out.flush();
        annotations.clear();
		}
		}
	
	out.close();
		
	}
}
	
	


