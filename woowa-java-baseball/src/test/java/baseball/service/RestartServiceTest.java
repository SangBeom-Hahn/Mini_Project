package baseball.service;

import baseball.domain.RestartStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RestartServiceTest {
    private RestartService restartService = new RestartService();
    private InputStream in;

    @AfterEach
    void afterEach() throws IOException {
        in.close();
    }

    @Test
    @DisplayName("1을 입력하면 게임 재시작")
    void restartTest() {
        //given
        String restartStatusNumber = "1";
        in = new ByteArrayInputStream(restartStatusNumber.getBytes());

        // when
        System.setIn(in);
        RestartStatus restartStatus = restartService.restartOrTerminate();

        // then
        assertThat(restartStatus).isEqualTo(RestartStatus.RESTART);
    }

    @Test
    @DisplayName("2을 입력하면 게임 종료")
    void endTest() {
        //given
        String restartStatusNumber = "2";
        in = new ByteArrayInputStream(restartStatusNumber.getBytes());

        // when
        System.setIn(in);
        RestartStatus restartStatus = restartService.restartOrTerminate();

        // then
        assertThat(restartStatus).isEqualTo(RestartStatus.END);
    }

    @ParameterizedTest
    @MethodSource("otherInput")
    @DisplayName("1, 2외 다른 입력을 하면 예외가 발생한다.")
    void RestartTest(String restartStatusNumber) {
        //given
        in = new ByteArrayInputStream(restartStatusNumber.getBytes());

        // when
        System.setIn(in);

        // then
        assertThatThrownBy(() -> restartService.restartOrTerminate())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1, 2 외에 다른 입력은 할 수 없습니다.");
    }

    private static Stream<Arguments> otherInput() {
        return Stream.of(
                Arguments.of("3"),
                Arguments.of("^"),
                Arguments.of("a"),
                Arguments.of("가")
        );
    }
}