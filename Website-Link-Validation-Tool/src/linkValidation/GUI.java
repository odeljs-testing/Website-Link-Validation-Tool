package linkValidation;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{

	TextField t;
	String searchUrl;
	Button searchB;
	
	
	GUI(){
		
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
	      
	      
	      //adding action listener
	      searchB.addActionListener(this);
	}
	
	
	
	
	
	public String getSearchUrl() {
		return searchUrl;
	}





	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}





	public void actionPerformed(ActionEvent e) {
		
		searchUrl = t.getText();
		
		setSearchUrl(searchUrl);
		
	}
	

}
