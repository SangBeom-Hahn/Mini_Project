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
        List<String> inputNumber = inputNumber();
        validateInputNumberSize(inputNumber);
        validateInputNumberHasDuplicate(inputNumber);

        return inputNumber;
    }

    private void validateInputNumberHasDuplicate(final List<String> inputNumber) {
        if (hasDuplicate(inputNumber)) {
            throw new IllegalArgumentException(INPUT_NUMBER_HAS_NOT_DUPLICATE_EXCEPTION.message);
        }
    }

    private static boolean hasDuplicate(final List<String> inputNumber) {
        return inputNumber.stream().distinct().count() != INPUT_NUMBER_SIZE;
    }

    private void validateInputNumberSize(final List<String> inputNumber) {
        if (inputNumber.size() != 3) {
            throw new IllegalArgumentException(INPUT_NUMBER_LENGTH_EXCEPTION.message);
        }
    }

    public List<String> getBaseballNumber() {
        return Collections.unmodifiableList(baseballNumber);
    }
}
