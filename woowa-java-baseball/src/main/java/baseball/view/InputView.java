package baseball.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static baseball.utils.ExceptionConstant.InputException.INPUT_NUMBER_HAS_NOT_SPACE_EXCEPTION;
import static baseball.utils.ExceptionConstant.InputException.INPUT_NUMBER_HAS_NOT_SPECIAL_CHARACTER_EXCEPTION;
import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {

    public static final String GAME_RESTART_OR_END_ONE_OR_TWO_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    public static final String INPUT_MESSAGE = "숫자를 입력해주세요 : ";

    private InputView() {
    }

    public static List<String> inputNumber() {
        String inputNumber = readLine();
        validateInputNumberIsNumeric(inputNumber);
        validateHasWhiteSpace(inputNumber);
        return convertToStringList(inputNumber);
    }
    
    public static String inputRestartStatus() {
        String inputNumber = readLine();
        validateInputNumberIsNumeric(inputNumber);
        validateHasWhiteSpace(inputNumber);
        return inputNumber;
    }

    public static void printRestartStatusMessage() {
        System.out.println(GAME_RESTART_OR_END_ONE_OR_TWO_MESSAGE);
    }

    public static void printInputMessage() {
        System.out.println(INPUT_MESSAGE);
    }
    
    private static List<String> convertToStringList(String inputNumber) {
        return Arrays.stream(inputNumber.split(""))
          .collect(Collectors.toList());
    }

    private static void validateHasWhiteSpace(final String inputNumber) {
        if (inputNumber.chars().anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException(INPUT_NUMBER_HAS_NOT_SPACE_EXCEPTION.message);
        }
    }

    private static void validateInputNumberIsNumeric(final String inputNumber) {
        try {
            Integer.valueOf(inputNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_NUMBER_HAS_NOT_SPECIAL_CHARACTER_EXCEPTION.message);
        }
    }
}
