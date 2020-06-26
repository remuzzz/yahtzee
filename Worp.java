import java.util.Arrays;
import java.util.Scanner;

public class Worp {
	int[] worp = new int[5];

	void worpuitslag(int[] uitslag) {
		System.out.println("Dit is de worparray:" + Arrays.toString(uitslag));
		if (nummersvergelijken(uitslag) == true) {
			System.out.println("Hoera je hebt Yahtzee gegooid! Het spel is nu afgelopen.");
			System.exit(0); // spel is beëindigd
		}
	}

	public static boolean nummersvergelijken(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[0] != a[i]) {
				return false;
			}
		}

		return true;
	}

	void vasthouden(int[] blokkeerarray) {
		Arrays.fill(blokkeerarray, 0); // leegmaken,zodat het bij iedere worp weer vers is.
		System.out.println("Wat wil je vasthouden? (Bijv: 123)");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
			if (!checkinvoer(input))
			{
				input = scanner.nextLine();
			}
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			int temp = Character.getNumericValue(ch);
			int dobbelnummer = temp - 1; // als je 1 opgeeft,moet hij naar arrayplek 0
			blokkeerarray[dobbelnummer] = 1;
			System.out.println(Arrays.toString(blokkeerarray));

		}
	}

	boolean checkinvoer(String input) {
		if (input.length() > 5) {
			System.out.println("Je hebt meer dan vijf tekens ingevoerd. Probeer opnieuw");
			return false;
		}
		if (input.length() > 0) {
			switch (input.charAt(0)) {	
			case 'q':
			case 'Q':
			System.out.println("Je hebt q ingevoerd. Het spel wordt nu afgesloten.");
			System.exit(0);
			 break;
			case 'g':
			case 'G':
			System.out.println("Je hebt G ingevoerd. Toon geschiedenis");
			  break;
			}
			return true;
			
		} else {
			return true;
		}

	}
}
