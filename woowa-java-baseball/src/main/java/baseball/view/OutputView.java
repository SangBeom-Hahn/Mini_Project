package baseball.view;

public class OutputView {

    public static final int STRIKE_COUNT = 3;
    public static final int ZERO = 0;
    public static final String THREE_STRIKE = "3스트라이크";
    public static final String STRIKE = "스트라이크";
    public static final String BALL = "볼";
    public static final String NOTHING = "낫싱";
    public static final String GAME_START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    public static final String GAME_OVER_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

    public static void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public static void printGameOverMessage() {
        System.out.println(GAME_OVER_MESSAGE);
    }

    public static void printResultMessage(final int strikeCount, final int ballCount) {
        if (strikeCount == STRIKE_COUNT) {
            System.out.println(THREE_STRIKE);
        }
        if (strikeCount != ZERO && ballCount == ZERO) {
            System.out.println(strikeCount + STRIKE);
        }
        if (strikeCount == ZERO && ballCount != ZERO) {
            System.out.println(ballCount + BALL);
        }
        if (strikeCount == ZERO && ballCount == ZERO) {
            System.out.println(NOTHING);
        }
        if (strikeCount != ZERO && ballCount != ZERO) {
            System.out.println(ballCount + BALL + " " + strikeCount + STRIKE);
        }
    }
}
