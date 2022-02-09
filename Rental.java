import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}
	
	public double calculateCharge(int daysRented){
		return this.getVideo().pc.applyPolicy(daysRented);
    }

	public int calculatePoint(int dayRented){
		int point = video.pc.getPoint();

		if(dayRented>getDaysRentedLimit()){
			point -=Math.min(point, video.getLateReturnPointPenalty());
		}
		return point;
	}

	public Video getVideo() {
		return video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) {
			this.status = 1;
			returnDate = new Date() ;
		}
	}
	public double getCharge() {
		int daysRented = getDaysRented();
		return calculateCharge(daysRented);
	}

	public int getPoint(){
		int daysRented = getDaysRented();
		return calculatePoint(daysRented);
	}

	public int getDaysRented() {
		long diff;
		if (getStatus() == 1) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();
		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		int daysRented = calcDaysRented(diff);
		return daysRented;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented = this.getDaysRented();
		if ( daysRented <= 2) return limit ;
		limit = video.vt.getLimit();
		return limit ;
	}

	public int calcDaysRented(long diff) {
		return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
	}

}
