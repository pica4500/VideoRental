import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	// SRP violation - Long Method
	// Feature Envy
	// Report Generation 등으로 분리 가능
	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {

			int daysRented = 0;

			// duplication
			// Status
			if (each.getStatus() == 1) { // returned Video
				long diff = each.getReturnDate().getTime() - each.getRentDate().getTime();
				daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
			} else { // not yet returned
				long diff = new Date().getTime() - each.getRentDate().getTime();
				daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
			}

			// Strategy 혹은 Subtyping
			// magic number
			double eachCharge = each.getVideo().pc.applyPolicy(daysRented);
			int eachPoint = each.calculatePoint(daysRented);
			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

		printTotalPoint(totalPoint);
		return result ;
	}

	private void printTotalPoint(int totalPoint) {
		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
	}
}
