package linkValidation;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Program implements ActionListener {

	TextField t;
	static String searchUrl;
	Button searchB;
	
	
		Program(){
			
			//creating a frame
			Frame f = new Frame();
			
			//creating a label
			Label searchURL = new Label("Search URL:");
			
			//creating a button
			searchB = new Button("Search");
			
			//creating a text field
			t = new TextField();
			
		    // setting position of above components in the frame  
		      searchURL.setBounds(20, 80, 80, 30);  
		      t.setBounds(20, 100, 80, 30);  
		      searchB.setBounds(100, 100, 80, 30);  
		  
		      //adding action listener
		      searchB.addActionListener(this);
		      
		      // adding components into frame    
		      f.add(searchURL);  
		      f.add(searchB);  
		      f.add(t);  
		  
		      // frame size 300 width and 300 height    
		      f.setSize(400,300);  
		  
		      // setting the title of frame  
		      f.setTitle("Techwerks Webpage Validation Tool");   
		          
		      // no layout  
		      f.setLayout(null);  
		
		      //setting visibility of frame
		      f.setVisible(true);
		      
		     
		}





		public void actionPerformed(ActionEvent e) {
			
			searchUrl = t.getText();
			
		}
		

	
		
	

		public static void main(String[] args) {
	
			int userSelection = 0;
			String domain = args[0];
			String url = "";
	
			Program program = new Program();
			
			System.out.println(searchUrl);
	
		}
}



		/*Scanner sc = new Scanner(System.in);
		
		System.out.println("What would you like to do with the provided link?");
		System.out.println("1 - Search for all instances of a link in the given domain?");
		
		
		//Create switch statement navigation menu
		switch(userSelection = sc.nextInt()) {
		case 1: 
			//get list needed
			//call method with list
			
			System.out.println("Provide the link you would like to search for:");
			url = sc.next();
			System.out.println(url);
			ListBuilder list = new ListBuilder(domain);
			list.buildWebpageList();
			list.SearchForUrl(url);
			break;
			
		}
		

	}*/


