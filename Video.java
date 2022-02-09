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

	// TODO : strategy 활용을 위한 getter, setter
	public void setPriceCodeStrategy(PriceCodeStrategy pc){
		this.pc = pc;
	}

	// TODO : strategy 활용을 위한 getter, setter
	public void setVideoTypeStrategy(VideoTypeStrategy vt) {
		 this.vt = vt;
	}

	public int getLateReturnPointPenalty() {
		return this.vt.getPenalty();
	}

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

	public String printPriceTag(){
		return String.format("\tTitle: %s \tPriceCode: %d", title, pc.getPriceCode());
	}
}
