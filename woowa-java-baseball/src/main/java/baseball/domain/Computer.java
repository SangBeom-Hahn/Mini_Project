package baseball.domain;

import baseball.utils.InputConstant;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static baseball.utils.InputConstant.INPUT_NUMBER_SIZE;

public class Computer {
    public static final int MIN_DIGIT = 1;
    public static final int MAX_DIGIT = 9;
    private final List<String> randomNumber;

    public Computer() {
        this.randomNumber = generateRandomNumber();
    }

    private List<String> generateRandomNumber() {
        List<String> randomNumber = new ArrayList<>();
        while (isRandomNumberSizeLessThatLimit(randomNumber)) {
            String digit = generateDigit();
            if (isContain(randomNumber, digit)) {
                continue;
            }

            randomNumber.add(digit);
        }

        return randomNumber;
    }

    private String generateDigit() {
        return covertToString(Randoms.pickNumberInRange(MIN_DIGIT, MAX_DIGIT));
    }

    private boolean isRandomNumberSizeLessThatLimit(final List<String> randomNumber) {
        return randomNumber.size() < INPUT_NUMBER_SIZE;
    }

    private boolean isContain(
      final List<String> randomNumber,
      final String digit
    ) {
        return randomNumber.contains(digit);
    }

    private String covertToString(final int digit) {
        return String.valueOf(digit);
    }

    public List<String> getRandomNumber() {
        return Collections.unmodifiableList(randomNumber);
    }
}
