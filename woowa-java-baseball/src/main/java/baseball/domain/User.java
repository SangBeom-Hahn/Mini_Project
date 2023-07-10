package baseball.domain;

import java.util.Collections;
import java.util.List;

public class User {
    private Baseball baseball;

    public void setBaseball(final Baseball baseball) {
        this.baseball = baseball;
    }

    public List<String> getBaseball() {
        return baseball.getBaseballNumber();
    }
}
