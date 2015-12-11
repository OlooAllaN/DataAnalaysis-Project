import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.Target;
import java.security.PublicKey;
import java.sql.Date;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.tree.ExpandVetoException;
/**
 * @author allanoloo 
 * CS: 220 Date:
 * 4/16/2015 
 * Final Lab
 *
 */
public class DataAnalaysis {
	private ArrayList<Integer> grades = new ArrayList<>();
	private String fileInput;

	public DataAnalaysis() throws FileNotFoundException {
		openGradeList();
	}

	/**
	 * Method allows user input to open file.
	 * 
	 */
	
	public void openGradeList() throws FileNotFoundException {
		try {
			Scanner file = new Scanner(System.in);
			System.out.println("Please enter file name?");
			fileInput = file.next();
			File gradeList = new File(fileInput);
			Scanner in = new Scanner(gradeList);
			while (in.hasNextInt()) {
				int grade = in.nextInt();
				grades.add((int) grade);
			}
			Collections.sort(grades);
			in.close();
		}
		/**
		 * If file does not exist the user is notified by file does not exist.
		 * 
		 */
		
		catch (FileNotFoundException e) {
			System.out.println("File does not Exist!");
			openGradeList();
			
		}
		Collections.sort(grades);
	}

	public ArrayList<Integer> getGrades() {
		return grades;
	}

	/**
	 * Going through values in grades by adding them then dividing by the
	 * length.
	 * @param grades going through this list.
	 * @return
	 * 
	 */
	
	public int mean() {
		int sum = 0;
		for (int i = 0; i < grades.size(); i++) {
			sum += grades.get(i);
		}
		return (sum / grades.size());
	}

	/**
	 * Method that calculates the standard deviation from scores.
	 * @param grades
	 * @return
	 * 
	 */
	
	public int standardDeviation() {
		int meanValue = mean();
		int sum = 0;
		for (int i = 0; i < grades.size(); i++) {
			sum += Math.pow(grades.get(i) - meanValue, 2);
		}
		return (int) Math.sqrt(sum / grades.size());

	}

	/**
	 * Finds the middle value from the list grades.
	 * @return
	 * 
	 */
	
	public int median() {
		if (grades.size() % 2 == 1) {
			return grades.get(grades.size() / 2);
		} else {
			int gradeX = grades.get(grades.size() / 2);
			int gradeY = grades.get(grades.size() / 2 - 1);
			gradeX += gradeY;
			return (gradeX / 2);
		}
	}

	/**
	 * Retrieves the highest value from the list.
	 * @param grades
	 * @return
	 * 
	 */
	
	public int highestValue() {
		int maxNumber = grades.get(0);
		for (int i = 0; i < grades.size(); i++) {
			if (grades.get(i) > maxNumber) {
				maxNumber = grades.get(i);
			}
		}
		return maxNumber;
	}

	/**
	 * Retrieves lowest number value from the list.
	 * @param grades
	 * @returns
	 * 
	 */
	
	public int smallestValue() {
		int minNumber = grades.get(0);
		for (int i = 0; i < grades.size(); i++) {
			if (grades.get(i) < minNumber) {
				minNumber = grades.get(i);
			}
		}
		return minNumber;

	}

	/**
	 * Subtracts the Maximum number from the Minimum Number to get the range.
	 * @param grades
	 * @return
	 * 
	 */
	
	public int range() {
		return (highestValue() - smallestValue());

	}

	/**
	 * Collects lower Quartile grades from lowerGrades list.
	 * @return
	 * 
	 */
	
	public int Quartile1() {
		ArrayList<Integer> lowerGrades = new ArrayList<Integer>();
		for (int i = 0; i < grades.size(); i++) {
			if (grades.get(i) < median()) {
				lowerGrades.add(grades.get(i));
			}

		}
		if (lowerGrades.size() % 2 == 1) {
			return lowerGrades.get(lowerGrades.size() / 2);
		} else {
			int gradeX = lowerGrades.get(lowerGrades.size() / 2);
			int gradeY = lowerGrades.get(lowerGrades.size() / 2 - 1);
			gradeX += gradeY;
			return (gradeX / 2);
		}
	}

	/**
	 * Collects top Quartile values from lowerGrades list.
	 * @return
	 * 
	 */
	
	public int Quartile3() {
		ArrayList<Integer> upperGrades = new ArrayList<Integer>();
		for (int i = 0; i < grades.size(); i++) {
			if (grades.get(i) > median()) {
				upperGrades.add(grades.get(i));
			}

		}
		if (upperGrades.size() % 2 == 1) {
			return upperGrades.get(upperGrades.size() / 2);
		} else {
			int gradeX = upperGrades.get(upperGrades.size() / 2);
			int gradeY = upperGrades.get(upperGrades.size() / 2 - 1);
			gradeX += gradeY;
			return (gradeX / 2);
		}
	}

	/**
	 * Kyle Johnson CS Major - Advised me to create a method that graphs all
	 * values. This method collects all values greater than 90 or lower than 40
	 * and graphs them.
	 * 
	 */
	
	public void histoGraph() {
		String a = "", b = "";
		for (int i = 0; i < grades.size(); i++) {
			if (grades.get(i) > 90) {
				a += "X";
			} else if (grades.get(i) < 40) {
				b += "Y";
			}
		}
		System.out.println("The \'X\' represents the grades that are either greater than 90 or less than 40 in a graph.");
		System.out.println("");
		System.out.println("_____________________________");
		System.out.println(fileInput + "|" + a);
		System.out.println(fileInput + "|" + b);
		System.out.println("______________________________");
	}
	/**
	 * This method allows me to search through a for certain grades.
	 * @param grade
	 * @return
	 */

	public int searchGrades(int grade) {
		for (int i = 0; i < grades.size(); i++) {
			if (grades.get(i).equals(grade)) {
				System.out.println("Grade was found!");
				return i;
			}
		}
		System.out.println("Grade does not exist");
		return -1;
	}
	/**
	 * This method allows me to sort values from least to greatest.
	 * 
	 */

	public void selectionSort() {
		int i, j, minIndex, temp;
		int n = grades.size();
		for (i = 0; i < n - 1; i++) {
			minIndex = i;
			for (j = i + 1; j < n; j++) {
				if (grades.get(j) < grades.get(minIndex)) {
					minIndex = j;
				}
				if (minIndex != i) {
					temp = grades.get(i);
					grades.set(i, minIndex);
					grades.set(minIndex, temp);
				}

			}
		}
	}
	/**
	 * Prints out grades list in a sorted order
	 * 
	 */
   @Override
   public String toString() {
	return grades.toString();
   }
}
