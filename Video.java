import strategies.PriceCodeStrategy;
import strategies.VideoTypeStrategy;

import java.util.Date;

public class Video {
	private String title ;

	PriceCodeStrategy pc;
	VideoTypeStrategy vt;

	private Date registeredDate ;
	private boolean rented ;

	 public Video(String title, PriceCodeStrategy pc, VideoTypeStrategy vt, Date registeredDate){
		this.setTitle(title);
		this.pc = pc;
		this.vt = vt;
		this.registeredDate = registeredDate;
	}

	public void setPriceCodeStrategy(PriceCodeStrategy pc){
		this.pc = pc;
	}

	public void setVideoTypeStrategy(VideoTypeStrategy vt) {
		 this.vt = vt;
	}

	public int getLateReturnPointPenalty() {
		return this.vt.getPenalty();
	}

	public int getPriceCode() {
		return this.pc.getPriceCode();
	}

//	public void setPriceCode(int priceCode) {
//		this.priceCode = priceCode;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public int getVideoType() {
		return vt.getVideoType();
	}

//	public void setVideoType(int videoType) {
//		this.videoType = videoType;
//	}
}
