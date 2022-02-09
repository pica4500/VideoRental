package strategies.video_types;

import strategies.VideoTypeStrategy;

public class VHSVideoType implements VideoTypeStrategy {

    public int getVideoType() {
        return 1;
    }
    public int getPenalty() {
        return 1;
    }
    public int getLimit() {
        return 5;
    }
}
