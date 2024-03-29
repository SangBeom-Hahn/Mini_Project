package baseball.controller;

import baseball.domain.*;
import baseball.utils.InputConstant;

import static baseball.domain.RestartStatus.*;
import static baseball.service.RestartService.restartOrTerminate;
import static baseball.service.ResultService.result;
import static baseball.view.InputView.printInputMessage;
import static baseball.view.OutputView.*;

public class BaseballController {
    private User user;
    private Computer computer;
    private Result result;
    private RestartStatus restartStatus;

    public BaseballController() {
        user = new User();
        restartStatus = RESTART;
    }

    public void run() {
        gameStart();
        while (restartStatus != END) {
            playGame();
            gameOver();
        }
    }

    private void gameStart() {
        printGameStartMessage();
        computer = new Computer();
    }

    private void playGame() {
        printInputMessage();
        user.setBaseball(new Baseball());
        result = result(user.getBaseball(), computer.getRandomNumber());
        printResultMessage(result.getStrikeCnt(), result.getBallCnt());
    }

    private void gameOver() {
        if (result.checkAllStrike()) {
            printGameOverMessage();
            restartStatus = restartOrTerminate();
        }
    }
}
