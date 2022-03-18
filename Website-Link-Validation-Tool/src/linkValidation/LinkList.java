package linkValidation;

public class LinkList {
	
	Node head = null;
	
	static class Node{
		String webpage;
		String url;
		Node next;
		
		Node(String webpage, String url){
			this.webpage = webpage;
			this.url = url;
			next = null;
		}
	}
	
	//insert
	public LinkList insert(String webpage, String url, LinkList list) {
		
		Node newNode = new Node(webpage, url);
		
		newNode.next = null;
		
		if(list.head == null) {
			list.head = newNode;
		}else {
			
			Node last = list.head;
			while(last.next != null) {
				last = last.next;
			}
			
			last.next = newNode;
		}
		
		return list;
		
	
		
	}
	
	
	//search
	 public void search(String x, LinkList list)
	    {
	        Node current = list.head;    //Initialize current
	        while (current != null)
	        {
	            if (current.url == x) {
	                System.out.println("Link: " + x + "Found on Webpage: " + current.webpage);    //data found
	            }
	                current = current.next;
	        }
	           //data not found
	    }
	 
	 public void printList(LinkList list) {
		 
		 
		 
		 Node current = list.head;    //Initialize current
	        while (current != null)
	        {
	         
	        	System.out.println(current.webpage + " : " + current.url);
	        	
	                current = current.next;
	        }
	 }
}
