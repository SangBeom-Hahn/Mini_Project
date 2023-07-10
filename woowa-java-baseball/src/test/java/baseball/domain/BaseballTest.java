package baseball.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

class BaseballTest {
    @ParameterizedTest
    @MethodSource("characterNumber")
    @DisplayName("숫자가 아닌 값을 입력하면 예외가 발생")
    void throwExceptionByBaseballHasCharacterTest(String characterNumber) {
        // given
        InputStream in = new ByteArrayInputStream(characterNumber.getBytes());

        // when
        System.setIn(in);

        // then
        Assertions.assertThatThrownBy(Baseball::new)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 숫자에는 특수문자를 호함할 수 없습니다.");
    }

    private static Stream<Arguments> characterNumber() {
        return Stream.of(
                Arguments.of("1*2"),
                Arguments.of("a13")
        );
    }

    @ParameterizedTest
    @MethodSource("duplicateNumber")
    @DisplayName("중복 값을 입력하면 예외가 발생")
    void throwExceptionByBaseballHasDuplicateTest(String characterNumber) {
        // given
        InputStream in = new ByteArrayInputStream(characterNumber.getBytes());

        // when
        System.setIn(in);

        // then
        Assertions.assertThatThrownBy(Baseball::new)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 숫자에는 중복이 있을 수 없습니다.");
    }

    private static Stream<Arguments> duplicateNumber() {
        return Stream.of(
                Arguments.of("112"),
                Arguments.of("333")
        );
    }

    @ParameterizedTest
    @MethodSource("shortOrLongNumber")
    @DisplayName("3자리가 아닌 값을 입력하면 예외가 발생")
    void throwExceptionByBaseballShortOrLongTest(String characterNumber) {
        // given
        InputStream in = new ByteArrayInputStream(characterNumber.getBytes());

        // when
        System.setIn(in);

        // then
        Assertions.assertThatThrownBy(Baseball::new)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 숫자는 3자리입니다.");
    }

    private static Stream<Arguments> shortOrLongNumber() {
        return Stream.of(
                Arguments.of("1"),
                Arguments.of("12"),
                Arguments.of("1234")
        );
    }
}
