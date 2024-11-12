import java.util.Scanner;
import java.util.Random;

public class WordleKids {
	static int wordNum;
	static String ranWord;
	public static final String TEXT_GREEN = "\u001B[32m";
	public static final String TEXT_YELLOW = "\u001B[33m";
	public static final String TEXT_RED = "\u001B[31m";
	public static final String TEXT_RESET = "\u001B[0m";

	public static void main(String[] args) {
		setWords();
		clearScreen();
		getGuess();
	}
	// get words from user and pick a random word from given list, printing its
	// length
	private static String[] setWords() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many words?: ");
		wordNum = scanner.nextInt();

		String words[] = new String[wordNum + 1];
		for (int i = 1; i <= wordNum; i++) {
			System.out.print("Word " + i + ": ");
			words[i] = scanner.next();
		}
		int ranNum;
		Random random = new Random();
		ranNum = random.nextInt(1, wordNum + 1);
		ranWord = words[ranNum].toUpperCase();
		return words;
	}
	// "clear" console after getting list of words by filling with new lines
	private static void clearScreen() {
		int i = 0;
		while (i < 50) {
			System.out.println();
			i++;
		}
	}
	// have the user guess letters. if correct, letter is green, wrong, red, not right spot, yellow
	private static void getGuess() {
		int attempt = 0;
		System.out.println(ranWord.length() + " letter word");
		do {
			Scanner scanner = new Scanner(System.in);
			String guess = scanner.next().toUpperCase();
			if (guess.length() != ranWord.length()) {
				System.out.println("ERROR: CHECK WORD LENGTH");
				guess = scanner.next();
			}
			String[] guess1 = guess.split("(?!^)");
			String[] ranWord1 = ranWord.split("(?!^)");
			int initChar = 0, counter = 0, correct = 0, newLine = 0;
			;
			for (int i = 0; i < guess.length(); i++) {
				if (guess1[i].contentEquals(ranWord1[i])) {
					System.out.print(TEXT_GREEN + guess1[i] + TEXT_RESET);
					correct++;
					newLine++;
					if(correct == guess.length()) {
						System.out.println();
						System.out.println("YOU WIN!");
						break;
					}
					if(newLine == guess.length()) {
						System.out.println();
						attempt++;
					}
				}else if (!containsHelper(ranWord, guess)) {
					System.out.print(TEXT_RED + guess1[i] + TEXT_RESET);
					newLine++;
					if(newLine == guess.length()) {
						System.out.println();
						attempt++;
					}
				}

				else if (!guess1[i].contentEquals(ranWord1[i])) {
					for (int count = 0; count < guess.length(); count++) {
						if (guess1[i].contentEquals(ranWord1[count])) {
							System.out.print(TEXT_YELLOW + guess1[i] + TEXT_RESET);
							newLine++;
							if(newLine == guess.length()) {
								System.out.println();
								attempt++;
							}
						}
					}
				}
				// compare user input to dictionary text file. if not valid word, enter new
				// word.
			}
			// user has 6 guesses to get right word. after guess 6 print the correct word.
		} while(attempt < 6);
		if(attempt == 6) {
			System.out.println(TEXT_RED + "The word was " + ranWord);
		}
	}

	private static boolean containsHelper(String word, String word2) {
		for (int i = 0; i < word2.length(); i++) {
			if (!word.contains(String.valueOf(word2.charAt(i)))) {
				return false;
			}
		}
		return true;

	}
}