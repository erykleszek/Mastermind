package org.example;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    public static void main(String[] args) {
        System.out.println("Witaj w grze Mastermind!");
        System.out.println("Zgadnij 4 cyfrowy kod składający się z cyfr od 1 do 6.");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int codeLength = 4;
        int maxDigit = 6;
        int[] secretCode = new int[codeLength];
        int[] userCode = new int[codeLength];
        boolean guessed = false;

        for (int i = 0; i < codeLength; i++) {
            secretCode[i] = random.nextInt(maxDigit) + 1;
        }

        while (!guessed) {
            System.out.println("Wprowadź swoją próbę:");
            String guess = scanner.nextLine();

            if (!guess.matches("\\d{" + codeLength + "}")) {
                System.out.println("Wprowadź dokładnie " + codeLength + " cyfry.");
                continue;
            }

            try {
                for (int i = 0; i < codeLength; i++) {
                    userCode[i] = Character.getNumericValue(guess.charAt(i));
                    if (userCode[i] < 1 || userCode[i] > maxDigit) {
                        throw new NumberFormatException();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź liczby od 1 do " + maxDigit + ".");
                continue;
            }

            int identicalButNotInPlace = 0;
            int identicalAndInPlace = 0;
            boolean[] countedInUserCode = new boolean[codeLength];
            boolean[] countedInSecretCode = new boolean[codeLength];

            for (int i = 0; i < codeLength; i++) {
                if (userCode[i] == secretCode[i]) {
                    identicalAndInPlace++;
                    countedInUserCode[i] = true;
                    countedInSecretCode[i] = true;
                }
            }

            for (int i = 0; i < codeLength; i++) {
                if (!countedInUserCode[i]) {
                    for (int j = 0; j < codeLength; j++) {
                        if (!countedInSecretCode[j] && userCode[i] == secretCode[j]) {
                            identicalButNotInPlace++;
                            countedInUserCode[i] = true;
                            countedInSecretCode[j] = true;
                            break;
                        }
                    }
                }
            }

            if (identicalAndInPlace == codeLength) {
                guessed = true;
                System.out.println("Gratulacje! Zgadłeś kod!");
            } else {
                System.out.println("Liczba identycznych cyfr na swoim miejscu: " + identicalAndInPlace);
                System.out.println("Liczba identycznych cyfr nie na swoim miejscu: " + identicalButNotInPlace);
            }
        }

        scanner.close();
    }
}