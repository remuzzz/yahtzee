import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class yahtzeeMain {

	public static void main(String[] args) {
		YahtzeeSpel spel = new YahtzeeSpel();
		spel.setspelers();
		spel.spelen();

	}
}

class YahtzeeSpel {
	ArrayList<Dobbelsteen> dobbelsteenarray = new ArrayList<Dobbelsteen>();
	ArrayList<Speler> spelersoverzicht = new ArrayList<Speler>();
	int[] blokkeerarray = { 0, 0, 0, 0, 0 }; // standaard niets geblokkeerd
	int actievespelerid;
	Dobbelsteen werpen = new Dobbelsteen();

	YahtzeeSpel() {

		for (int i = 0; i < 5; i++) {
			Dobbelsteen dobbelsteen = new Dobbelsteen();
			dobbelsteenarray.add(dobbelsteen);
		}

	}

	void setspelers() {
		System.out.println("Met hoeveel spelers zijn jullie? (Voer getal in)");
		Scanner scanner = new Scanner(System.in);
		int y = scanner.nextInt();
		for (int i = 0; i < y; i++) {
			Speler speler = new Speler();
			System.out.println("Speler " + (i + 1) + ". Voer je naam in:");
			Scanner scanner2 = new Scanner(System.in);
			speler.naam = scanner2.nextLine();
			speler.id = i;
			spelersoverzicht.add(speler);
		}

	}

	void spelen() {
		System.out.println("Welkom bij Yahtzee. Je mag vijf keer achter elkaar gooien en hebt gewonnen als "
				+ "alle vijf de dobbelstenen hetzelfde zijn. ");
		System.out.println("Druk op Enter om te gooien, Q om te eindigen en G om je worpgeschiedenis te bekijken.");

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if (input.equals("q")) {
			System.out.println("Je hebt q ingevoerd. Het spel wordt nu afgesloten.");
			System.exit(0);
		} else {

			Worp worp = new Worp();

			int y = -1; // aantal beurten gegooid, i = aantal dobbelstenen gegooid per beurt
			boolean result = false;
			while (!result) {

				Speler actievespeler = spelersoverzicht.get(actievespelerid); // haal de actieve speler op uit arraylist
																				// met spelers

				y++;
				int beurt = (y + 1); // omdat de array begint met 0, en de beurt met 1
				int i = -1; // i moet bij de eerste worp 0 zijn in de for-loop.

				System.out.println(actievespeler.naam + ", je hebt gegooid:");
				System.out.println("1	2	3	4	5");
				for (Dobbelsteen dobbelsteen : dobbelsteenarray) {
					i++;

					if (blokkeerarray[i] == 0) { // betekent dat hij vrij is,werp opnieuw
						dobbelsteen.waarde = werpen.werpen();
					}
					worp.worp[i] = dobbelsteen.waarde; // voeg toe aan de Worp-array

					actievespeler.worpgeschiedenis[y][i] = dobbelsteen.waarde; // voeg toe aan de geschiedenis-array

				}
				worp.worpuitslag(worp.worp); // toon worpuitslag en check yahtzee
				System.out.println(""); // nieuwe regel eronder

				if (beurt % 5 == 0) { // volgende speler aan de beurt
					spelerswissel(beurt, actievespelerid);
					input = scanner.nextLine(); // Nieuwe speler, betekent opnieuw gooien.

				} else {
					worp.vasthouden(blokkeerarray, actievespeler); // vasthouden laten zien
				}

			}
		}
	}

	void spelerswissel(int beurt, int spelersid) {

		int aantalspelers = spelersoverzicht.size();

		if (aantalspelers > 1) { // alleen wisselen bij meerdere spelers
			if (actievespelerid + 1 == aantalspelers) { // array begint altijd bij 0 vandaar +1
				actievespelerid = 0; // begin weer bij de eerste speler
				
			} else {
				actievespelerid++;
			
			}
			Speler actievespeler = spelersoverzicht.get(actievespelerid);
			Arrays.fill(blokkeerarray, 0); // blokkeerarray leegmaken
			System.out.println(">>>>> BEURTWISSEL <<<<<");
			System.out.println(">>>>> De volgende speler is aan de beurt: " + actievespeler.naam + ". Werp met Enter.");
		}
	}

}
