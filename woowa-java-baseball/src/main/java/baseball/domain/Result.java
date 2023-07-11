package baseball.domain;

import baseball.utils.InputConstant;

public class Result {
    private int strikeCnt;
    private int ballCnt;

    public Result(final int strikeCnt, final int ballCnt) {
        this.strikeCnt = strikeCnt;
        this.ballCnt = ballCnt;
    }

    public int getStrikeCnt() {
        return strikeCnt;
    }

    public int getBallCnt() {
        return ballCnt;
    }
    
    public boolean checkAllStrike() {
        return strikeCnt == InputConstant.INPUT_NUMBER_SIZE;
    }
}
