package baseball.service;

import baseball.domain.RestartStatus;
import baseball.utils.InputConstant;

import static baseball.utils.ExceptionConstant.RestartException.RESTART_OR_END_ONE_OR_TWO_EXCEPTION;
import static baseball.view.InputView.*;

public class RestartService {
    public static RestartStatus restartOrTerminate() {
        printRestartStatusMessage();
        String restartStatus = inputRestartStatus();
        validateRestartStatusNotOneOrTwo(restartStatus);
        if (restartStatus.equals(InputConstant.INPUT_RESTART)) {
            return RestartStatus.RESTART;
        }
        return RestartStatus.END;
    }

    private static void validateRestartStatusNotOneOrTwo(final String restartStatus) {
        if (!restartStatus.equals(InputConstant.INPUT_RESTART) && !restartStatus.equals(InputConstant.INPUT_END)) {
            throw new IllegalArgumentException(RESTART_OR_END_ONE_OR_TWO_EXCEPTION.message);
        }
    }
}
