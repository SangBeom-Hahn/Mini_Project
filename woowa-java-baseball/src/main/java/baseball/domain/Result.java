package baseball.domain;

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
}
