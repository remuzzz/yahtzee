import java.util.Arrays;
import java.util.Scanner;

public class Speler {
	int[][] worpgeschiedenis = new int[555][5];
	String naam;
	int id;

	void geschiedenis() {
		int a = 0;
		for (int arr1D[] : worpgeschiedenis) {
			for (int i : arr1D) {

				if (i != 0) {
					a++;
					System.out.print(i + "	");

					if (a % 5 == 0) { // iedere vijfde keer een streepje
						System.out.print(" ||	");
					}
				}

			}
		}
		System.out.println("");// lege regel eronder
	}
}