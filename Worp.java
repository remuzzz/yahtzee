import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Worp {
	int[] worp = new int[5];

	void worpuitslag(int[] uitslag) {
		for (int a : uitslag) {
			System.out.print(a + "	"); // toon de worp
		}

		if (nummersvergelijken(uitslag) == true) {
			System.out.println(">>>> Hoera je hebt Yahtzee gegooid! Het spel is nu afgelopen. <<<<");
			System.exit(0); // spel is beëindigd
		}
	}

	public static boolean nummersvergelijken(int[] a) { // wordt true als alle nummers hetzelfde zijn
		for (int i = 0; i < a.length; i++) {
			if (a[0] != a[i]) {
				return false;
			}
		}

		return true;
	}

	void vasthouden(int[] blokkeerarray, Speler actievespeler) {
		Arrays.fill(blokkeerarray, 0); // leegmaken,zodat het bij iedere worp weer vers 0 is.
		System.out.println("Welke dobbelstenen wil je vasthouden? (Bijv: 123)");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		if (input.equals("") == false) { // een enter laat de charAt crashen,vandaar deze check
			switch (input.charAt(0)) {
			case 'q':
			case 'Q':
				System.out.println("Je hebt q ingevoerd. Het spel wordt nu afgesloten.");
				System.exit(0);
				break;
			case 'g':
			case 'G':
				System.out.println("Je hebt G ingevoerd. Hieronder is de geschiedenis van je worpen:");
				actievespeler.geschiedenis();
				break;
			}
			while (checkinvoer(input) != "") {
				System.out.println(checkinvoer(input) + " Probeer het opnieuw:");
				input = scanner.nextLine();
			}
		}

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			int temp = Character.getNumericValue(ch);
			int dobbelnummer = temp - 1; // als je 1 opgeeft,moet hij naar arrayplek 0
			blokkeerarray[dobbelnummer] = 1;
		}

	}

	String checkinvoer(String input) {
		String foutmelding = "";
		if (input.length() > 5) {
			foutmelding = "Je hebt meer dan vijf tekens ingevoerd.";
		}
		if (!input.chars().allMatch(Character::isDigit)) {
			foutmelding = "Je hebt een letter of ander teken ingevoerd. Voer getallen in om de dobbelstenen vast te zetten.";
		} else if (input.length() > 0) {
			for (int i = 0; i < input.length(); i++) {
				int a = Character.getNumericValue(input.charAt(i));
				if (a > 5) {
					foutmelding = "Je hebt een dobbelsteen ingevuld die niet bestaat. Je invoer was groter dan 5.";
				}
			}

		}
		return foutmelding;
	}
}
