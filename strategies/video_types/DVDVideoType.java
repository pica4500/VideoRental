package strategies.video_types;

import strategies.VideoTypeStrategy;

public class DVDVideoType implements VideoTypeStrategy {

    public int getVideoType() {
        return 3;
    }
    public int getPenalty() {
        return 3;
    }
    public int getLimit() {
        return 2;
    }
}
