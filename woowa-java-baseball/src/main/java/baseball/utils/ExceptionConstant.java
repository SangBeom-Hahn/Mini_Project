package baseball.utils;

public interface ExceptionConstant {
    enum InputException {
        INPUT_NUMBER_HAS_NOT_SPACE_EXCEPTION("입력 숫자에 공백이 없어야 합니다."),
        INPUT_NUMBER_HAS_NOT_SPECIAL_CHARACTER_EXCEPTION("입력 숫자에는 특수문자를 호함할 수 없습니다."),
        INPUT_NUMBER_HAS_NOT_DUPLICATE_EXCEPTION("입력 숫자에는 중복이 있을 수 없습니다."),
        INPUT_NUMBER_LENGTH_EXCEPTION("입력 숫자는 3자리입니다.");

        public final String message;

        InputException(final String message) {
            this.message = message;
        }
    }

    enum RestartException {
        RESTART_OR_END_ONE_OR_TWO_EXCEPTION("1, 2 외에 다른 입력은 할 수 없습니다.");

        public final String message;

        RestartException(final String message) {
            this.message = message;
        }
    }
}
