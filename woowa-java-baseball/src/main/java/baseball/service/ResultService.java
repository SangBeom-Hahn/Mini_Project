package baseball.service;

import baseball.domain.Result;

import java.util.List;
import java.util.stream.Stream;

public class ResultService {
    public static Result result(
      final List<String> userBaseballNumber,
      final List<String> computerRandomNumber
    ) {
        int strikeCount = getStrikeCount(userBaseballNumber, computerRandomNumber);
        int ballCount = getBallCount(userBaseballNumber, computerRandomNumber);

        return new Result(strikeCount, ballCount);
    }

//    public Result result(User user, Computer computer) {
//        List<String> userBaseballNumber = user.getBaseball();
//        List<String> computerRandomNumber = computer.getRandomNumber();
//        int strikeCount = getStrikeCount(userBaseballNumber, computerRandomNumber);
//        int ballCount = getBallCount(userBaseballNumber, computerRandomNumber);
//
//        return new Result(strikeCount, ballCount);
//    }

    private static int getStrikeCount(final List<String> userBaseballNumber, final List<String> computerRandomNumber) {
        return (int) Stream.iterate(0, (a -> a + 1))
                .limit(3)
                .filter(idx -> isEachDigitEquals(userBaseballNumber, computerRandomNumber, idx))
                .count();
    }

    private static boolean isEachDigitEquals(final List<String> userBaseballNumber, final List<String> computerRandomNumber, final int idx) {
        return userBaseballNumber.get(idx).equals(computerRandomNumber.get(idx));
    }

    private static int getBallCount(final List<String> userBaseballNumber, final List<String> computerRandomNumber) {
        return (int) Stream.iterate(1, (a -> a + 1))
                .limit(9)
                .filter(digit -> isEachNumberContainsSameDigit(userBaseballNumber, computerRandomNumber, digit))
                .filter(digit -> isEachNumberNotSameLocation(userBaseballNumber, computerRandomNumber, digit))
                .count();
    }

    private static boolean isEachNumberContainsSameDigit(final List<String> userBaseballNumber, final List<String> computerRandomNumber, final int digit) {
        return userBaseballNumber.contains(String.valueOf(digit)) && computerRandomNumber.contains(String.valueOf(digit));
    }

    private static boolean isEachNumberNotSameLocation(final List<String> userBaseballNumber, final List<String> computerRandomNumber, final int digit) {
        return userBaseballNumber.indexOf(String.valueOf(digit)) != computerRandomNumber.indexOf(String.valueOf(digit));
    }
}
