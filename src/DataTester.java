import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import javax.management.openmbean.OpenDataException;


public class DataTester {
	private static NumberFormat format = NumberFormat.getInstance();

	public static void main(String [] args) throws FileNotFoundException {
		format.setMaximumFractionDigits(2);
		
		/**
		 * Allows for user input.
		 * 
		 */
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the Data Analizer Machine.");
		System.out.println("");
		System.out.println("Would you like to \"analyze\", \"graph\", or \"search\" a file?");
		String choice = in.next();
		
		/**
		 * creates an object from DataAnalaysis and calls all the methods
		 * 
		 */
		if (choice.equals("analyze")) {
			DataAnalaysis scores = new DataAnalaysis();
			ArrayList<Integer> grades = scores.getGrades();
			System.out.println(grades);		
			System.out.println("Mean: "+ format.format(scores.mean()));
			System.out.println("Standard Deviation: " +format.format(scores.standardDeviation()));
			System.out.println("Range: "+ scores.range());
			System.out.println("Highest Value: "+ scores.highestValue());
			System.out.println("Smallest Value: "+ scores.smallestValue());
			System.out.println("Median: "+ scores.median());
			System.out.println("Quartile 1: "+ scores.Quartile1());
			System.out.println("Quartile 3: "+ scores.Quartile3());
		} else if (choice.equals("graph")) {
			DataAnalaysis scores = new DataAnalaysis();
			scores.histoGraph();
			
		} else if (choice.equals("search")) {
			DataAnalaysis scores = new DataAnalaysis();
			Scanner input =  new Scanner(System.in);
			System.out.println("Please enter a value from \"0\" to \"100\" to search in the file!");
			int numbers = input.nextInt();
			scores.searchGrades(numbers);
			scores.selectionSort();
			System.out.println(scores);
		}
		
		
	}
}
