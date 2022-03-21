package linkValidation;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Program implements ActionListener {

	TextField urlTextField, domainTextField;
	String searchurl, domain;
	Button searchB;
	
	
		Program(){
			
			//creating a frame
			Frame f = new Frame();
			
			//creating searchurl label
			Label searchURL = new Label("Search URL:");
			
			//creating domain input label
			Label domainLabel = new Label("Domain:");
			
			
			//creating button for search
			searchB = new Button("Search");
			
			
			
			
			//creating text field for search
			urlTextField = new TextField();
			
			
			//create a text field for domain input
			domainTextField = new TextField();
			
			
			
		    // setting position of domain content
		      domainLabel.setBounds(20, 70, 80, 30);  
		      domainTextField.setBounds(20, 100, 400, 30);  
		       
		      
		    //setting position of searchurl content
		      searchURL.setBounds(20, 130, 80, 30);  
		      urlTextField.setBounds(20, 160, 400, 30);  
		      searchB.setBounds(180, 200, 80, 30);
		      
		  
		      //adding action listener
		      searchB.addActionListener(this);
		      
		      // adding components into frame    
		      f.add(searchURL);  
		      f.add(searchB);  
		      f.add(urlTextField);
		      f.add(domainLabel);
		      f.add(domainTextField);
		  
		      // frame size 300 width and 300 height    
		      f.setSize(600,450);  
		  
		      // setting the title of frame  
		      f.setTitle("Techwerks Webpage Validation Tool");   
		          
		      // no layout  
		      f.setLayout(null);  
		
		      //setting visibility of frame
		      f.setVisible(true);
		      
		     
		}





		public void actionPerformed(ActionEvent e) {
			
			searchurl = urlTextField.getText();
			
			domain = domainTextField.getText();
			
			System.out.println(searchurl);
			
			//search for the url
			ListBuilder list = new ListBuilder(domain);
			list.buildWebpageList();
			list.SearchForUrl(searchurl);
		}
		

	
		
	

		public static void main(String[] args) {
	
			Program program = new Program();
			
			
	
		}
}



