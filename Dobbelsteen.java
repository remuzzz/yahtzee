import java.util.Arrays;
import java.util.Random;

public class Dobbelsteen {
	int waarde;

	int werpen() {
		Random random = new Random();
		int a = random.nextInt(6) + 1;
		waarde = a;
		return a;

	}
}