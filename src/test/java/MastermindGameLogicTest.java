import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MastermindGameLogicTest {

    @Test
    public void testGenerateSecretCode() {
        int codeLength = 4;
        int maxDigit = 6;
        int[] secretCode = new int[codeLength];
        Random random = new Random();

        for (int i = 0; i < codeLength; i++) {
            secretCode[i] = random.nextInt(maxDigit) + 1;
        }

        for (int digit : secretCode) {
            assert(digit >= 1 && digit <= maxDigit);
        }
    }

    @Test
    public void testCheckUserGuess() {
        int[] secretCode = {1, 2, 3, 4};
        int[] userCode = {1, 3, 2, 4};

        int identicalAndInPlace = 0;
        int identicalButNotInPlace = 0;
        boolean[] countedInUserCode = new boolean[4];
        boolean[] countedInSecretCode = new boolean[4];

        for (int i = 0; i < 4; i++) {
            if (userCode[i] == secretCode[i]) {
                identicalAndInPlace++;
                countedInUserCode[i] = true;
                countedInSecretCode[i] = true;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (!countedInUserCode[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!countedInSecretCode[j] && userCode[i] == secretCode[j]) {
                        identicalButNotInPlace++;
                        countedInUserCode[i] = true;
                        countedInSecretCode[j] = true;
                        break;
                    }
                }
            }
        }

        assertEquals(2, identicalAndInPlace);
        assertEquals(2, identicalButNotInPlace);
    }
}