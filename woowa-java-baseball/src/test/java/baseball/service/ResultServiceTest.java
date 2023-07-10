package baseball.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ResultServiceTest {
    private ResultService resultService = new ResultService();

    @ParameterizedTest
    @MethodSource("userNumberAndComputerNumberForStrikeCount")
    @DisplayName("동일 값이 같은 위치에 있으면 스트라이크 수를 센다.")
    void strikeResultTest(
            List<String> userBaseballNumber,
            List<String> computerRandomNumber,
            int strikeCount) {
        // when
        int resultStrikeCount = resultService.result(userBaseballNumber, computerRandomNumber)
                .getStrikeCnt();

        // then
        assertThat(strikeCount).isEqualTo(resultStrikeCount);
    }

    private static Stream<Arguments> userNumberAndComputerNumberForStrikeCount() {
        return Stream.of(
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("1", "4", "5"),
                        1),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("1", "2", "5"),
                        2),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("1", "2", "3"),
                        3)
        );
    }

    @ParameterizedTest
    @MethodSource("userNumberAndComputerNumberForBallCount")
    @DisplayName("동일 값이 같은 위치에 있으면 스트라이크 수를 센다.")
    void ballResultTest(
            List<String> userBaseballNumber,
            List<String> computerRandomNumber,
            int ballCount) {
        // when
        int resultBallCount = resultService.result(userBaseballNumber, computerRandomNumber)
                .getBallCnt();

        // then
        assertThat(ballCount).isEqualTo(resultBallCount);
    }

    private static Stream<Arguments> userNumberAndComputerNumberForBallCount() {
        return Stream.of(
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("1", "4", "5"),
                        0),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("1", "2", "3"),
                        0),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("4", "5", "6"),
                        0),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("2", "4", "5"),
                        1),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("5", "4", "2"),
                        1),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("4", "3", "1"),
                        2),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("3", "1", "4"),
                        2),
                Arguments.of(
                        List.of("1", "2", "3"),
                        List.of("2", "3", "1"),
                        3)
        );
    }
}
