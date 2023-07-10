package baseball.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static baseball.utils.ExceptionConstant.InputException.INPUT_NUMBER_HAS_NOT_DUPLICATE_EXCEPTION;
import static baseball.utils.ExceptionConstant.InputException.INPUT_NUMBER_LENGTH_EXCEPTION;
import static baseball.utils.InputConstant.INPUT_NUMBER_SIZE;
import static baseball.view.InputView.inputNumber;

public class Baseball {
    private final List<String> baseballNumber;

    public Baseball() {
        this.baseballNumber = generateBaseballNumber();
    }

    private List<String> generateBaseballNumber() {
        String inputNumber = inputNumber();
        validateInputNumberSize(inputNumber);
        validateInputNumberHasDuplicate(inputNumber);

        return Arrays.stream(inputNumber.split(""))
                .collect(Collectors.toList());
    }

    private void validateInputNumberHasDuplicate(final String inputNumber) {
        if (hasDuplicate(inputNumber)) {
            throw new IllegalArgumentException(INPUT_NUMBER_HAS_NOT_DUPLICATE_EXCEPTION.message);
        }
    }

    private static boolean hasDuplicate(final String inputNumber) {
        return Arrays.stream(inputNumber.split("")).distinct().count() != INPUT_NUMBER_SIZE;
    }

    private void validateInputNumberSize(final String inputNumber) {
        if (inputNumber.length() != 3) {
            throw new IllegalArgumentException(INPUT_NUMBER_LENGTH_EXCEPTION.message);
        }
    }

    public List<String> getBaseballNumber() {
        return Collections.unmodifiableList(baseballNumber);
    }
}
