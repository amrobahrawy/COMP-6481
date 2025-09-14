/**
 * -------------------------------------------------------
	Assignment 3
 	Programming Part - Smart Priority Queue
 	Written by: Amro Elbahrawy - 40221760
 	Due Date: April 14 2024
 -------------------------------------------------------
 */


public class SPQ_Driver {

	/**
	 * Main method of SPQ Driver
	 * @param args for the main method
	 * Shows many cases that cover all the implemented methods in the SPQ class.
	 */
	public static void main(String[] args) {
		SPQ<Integer> spq1 = new SPQ<Integer>();
		
		/**
		 *Toggling the queue and removing the top using insert() and removeTop()
		 * Also includes extending the queue's size by 50%
		 */
		System.out.println();
		System.out.println("All cases will include insertions and top() deletions.");
		System.out.println("----------------------------------------");

		System.out.println("Case 1: isEmpty() method" );
		SPQ<Integer> spq2 = new SPQ<Integer>();
		if(spq2.isEmpty())
			System.out.println("The queue is empty.");
		
		System.out.println(spq2.insert(0, 5));
		
		if(spq2.isEmpty())
			System.out.println("The queue is empty.");
		else
			System.out.println("The queue is not empty. " + spq2.size());
		System.out.println("----------------------------------------");

		System.out.println();
		
		System.out.println("Case 2: Toggle() method");
		SPQ<Integer> spq3 = new SPQ<Integer>();
		System.out.println(spq3.insert(0, 3));
		System.out.println(spq3.insert(1, 11));
		System.out.println(spq3.insert(2, 2));
		System.out.println(spq3.insert(3, 4));
		System.out.println(spq3.insert(4, 12));
		System.out.println(spq3.top());
		System.out.println(spq3.state());
		spq3.toggle();
		System.out.println(spq3.state());
		System.out.println(spq3.top());
		
		spq3.toggle();
		System.out.println("----------------------------------------");
		
		

		
		System.out.println();
		System.out.println();
		
		System.out.println("Case 3: Multiple additions to the queue with automatic size extensions.");
		SPQ<Integer> spq4 = new SPQ<Integer>();

		System.out.println(spq4.size());
		if(spq4.isEmpty())
			System.out.println("The queue is empty.");
		
		System.out.println(spq4.insert(0, 3));
		System.out.println(spq4.insert(1, 11));
		System.out.println(spq4.insert(2, 2));
		System.out.println(spq4.insert(3, 4));
		System.out.println(spq4.insert(4, 12));
		System.out.println("----------------------------------------");
		System.out.println(spq4.insert(5, 53));
		System.out.println(spq4.insert(6, 8));
		System.out.println("----------------------------------------");
		System.out.println(spq4.insert(7, 9));
		System.out.println(spq4.insert(8, 45));

		System.out.println(spq4.insert(9, 3));
		System.out.println("----------------------------------------");


		System.out.println(spq4.insert(10, 7));
		System.out.println(spq4.insert(11, 4));
		System.out.println(spq4.size());
		/**
		 * Different cases of removeTop where the queue is a min and max heap
		 */
		
		System.out.println("----------------------------------------");
		
		System.out.println();
		System.out.println("Case 4: Removing entries from the queue.");

		System.out.println(spq4.removeTop());
		
		System.out.println(spq4.remove(0, 3));
		System.out.println(spq4.remove(8, 9));
		System.out.println(spq4.remove(9, 3));
		
		spq4.size();
		
		
		System.out.println("----------------------------------------");
		System.out.println();

		System.out.println("Case 5: Replacing keys and values");
		System.out.println(spq4.insert(8, 9));
		System.out.println(spq4.replaceKey(8, 45, 10));
		System.out.println(spq4.replaceKey(20,4, 10));
		System.out.println(spq4.replaceKey(3, 4, 0));
		System.out.println(spq4.top());
		
		System.out.println(spq4.replaceValue(20, 3, 10));
		System.out.println(spq4.replaceValue(7 , 9 ,15));
		System.out.println(spq4.replaceValue(3, 4 ,15));

		
		System.out.println("----------------------------------------");
		
	


	}

}
