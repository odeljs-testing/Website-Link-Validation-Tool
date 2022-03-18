package linkValidation;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		int userSelection = 0;
		String domain = args[0];
		String url = "";
		
		
		Scanner sc = new Scanner(System.in);
		
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
		

	}

}
