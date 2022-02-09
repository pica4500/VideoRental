package strategies.video_types;

import strategies.VideoTypeStrategy;

public class CDVideoType implements VideoTypeStrategy {

    public int getVideoType() {
        return 2;
    }
    public int getPenalty() {
        return 2;
    }
    public int getLimit() {
        return 3;
    }
}
