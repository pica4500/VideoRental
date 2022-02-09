import strategies.PriceCodeStrategy;
import strategies.VideoTypeStrategy;
import strategies.price_codes.NewReleaseType;
import strategies.price_codes.RegularType;
import strategies.video_types.CDVideoType;
import strategies.video_types.DVDVideoType;
import strategies.video_types.VHSVideoType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoFactory {

    public static final int REGULAR = 1;
    public static final int NEW_RELEASE = 2;

    public static final int VHS = 1;
    public static final int CD = 2;
    public static final int DVD = 3;

    public static Video createVideo(String title, int priceCode, int videoType) {
        return new Video(title, getCs(priceCode), getVs(videoType), new Date());
    }

    private static VideoTypeStrategy getVs(int videoType){
        switch (videoType) {
            case VHS:
                return new VHSVideoType();
            case CD:
                return new CDVideoType();
            case DVD:
                return new DVDVideoType();
        }
        throw new RuntimeException("video type is not correct");
    }

    private static PriceCodeStrategy getCs(int priceCode){
        switch (priceCode) {
            case REGULAR:
                return new RegularType(VideoFactory.REGULAR);
            case NEW_RELEASE:
                return new NewReleaseType(VideoFactory.NEW_RELEASE);
        }
        throw new RuntimeException("price code type is not correct");
    }

    public static List<Video> createInitialVideos() {
        List<Video> ret = new ArrayList<>();
        ret.add(new Video("v1", new RegularType(VideoFactory.REGULAR), new CDVideoType(), new Date()));
        ret.add(new Video("v2", new NewReleaseType(VideoFactory.NEW_RELEASE), new DVDVideoType(), new Date()));
        return ret;
    }
}
