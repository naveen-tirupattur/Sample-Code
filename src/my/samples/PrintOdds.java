/**
 * Class to print odd numbers
 */
package my.samples;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Naveen Tirupattur
 *
 */
public class PrintOdds {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int num = 0;
		String input="";
		ArrayList<Integer> arrayNums = new ArrayList<Integer>();
		while(!input.equals("Q"))
		{
			Scanner scanner = new Scanner(System.in);

			//Accept the input
			System.out.println("Enter a number or Q to Quit \n");
			input = scanner.nextLine();			

			//Check the input
			if(input.equals("Q"))
			{				
				if(arrayNums.size() == 0)
					System.out.println("You have not entered any input");
				else
					System.out.println("Printing Odd Numbers");
			}
			else
				try
			{
					//Add the input to arraylist
					arrayNums.add(Integer.parseInt(input));	
			}catch(NumberFormatException e)
			{
				System.out.println("You have entered an invalid input. Try Again");
			}

		}

		for(Integer i:arrayNums)
		{
			//Print the odd numbers
			if(((int)i%2) !=0) System.out.println(i+" Is Odd Number");
		}
	}
}
