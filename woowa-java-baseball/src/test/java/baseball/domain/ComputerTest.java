package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComputerTest {
    @Test
    @DisplayName("컴퓨터의 랜덤 수가 3자리로 만들어지는지 테스트")
    void ComputerRandomSizeTest() {
        // given
        Computer computer = new Computer();

        // when

        // then
        assertThat(computer.getRandomNumber()).hasSize(3);
    }

    @Test
    @DisplayName("컴퓨터의 랜덤 수가 중복 자리 수가 없는지 테스트")
    void ComputerTest() {
        // given
        Computer computer = new Computer();

        // when

        // then
        assertThat(computer.getRandomNumber()
                .stream()
                .distinct()
                .count())
                .isEqualTo(3);
    }
}