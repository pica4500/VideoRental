import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VRUI {
	private static Scanner scanner = new Scanner(System.in) ;
	// Seperate domain from presentation

	private List<Customer> customers = new ArrayList<Customer>() ;

	private List<Video> videos = new ArrayList<Video>() ;

	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers() ; break ;
				case 2: ui.listVideos() ; break ;
				case 3: ui.register("customer") ; break ;
				case 4: ui.register("video") ; break ;
				case 5: ui.rentVideo() ; break ;
				case 6: ui.returnVideo() ; break ;
				case 7: ui.getCustomerReport() ; break;
				case 8: ui.clearRentals() ; break ;
				case -1: ui.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public void printCustomerInfo(Customer customer){
		System.out.println("Name: " + customer.getName() +
				"\tRentals: " + customer.getRentals().size()) ;
		for ( Rental rental: customer.getRentals() ) {
			System.out.print(rental.getVideo().printPriceTag());
		}
	}

	public void clearRentals() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		//duplication
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			// query
			printCustomerInfo(foundCustomer);

			// modifier
			List<Rental> rentals = new ArrayList<Rental>() ;
			foundCustomer.setRentals(rentals);
		}
	}

	public Customer getCustomerByUserInput(){
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}
		if(foundCustomer == null){
			throw new RuntimeException("customer name not found");
		}
		return foundCustomer;
	}

	public void returnVideo() {
		Customer foundCustomer = getCustomerByUserInput() ;

		System.out.println("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;

		// encapsulate collection
		List<Rental> customerRentals = foundCustomer.getRentals() ;
		for ( Rental rental: customerRentals ) {
			if ( rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented() ) {
				rental.returnVideo();
				rental.getVideo().setRented(false);
				break ;
			}
		}
	}

	private void init() {
		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customers.add(james) ;
		customers.add(brown) ;

		videos = VideoFactory.createInitialVideos();

		// 하드코딩
		Rental r1 = new Rental(videos.get(0)) ;
		Rental r2 = new Rental(videos.get(1)) ;

		james.addRental(r1) ;
		james.addRental(r2) ;
	}

	public void listVideos() {
		System.out.println("List of videos");

		for ( Video video: videos ) {
			System.out.println(video.printPriceTag()) ;
		}
		System.out.println("End of list");
	}

	public void listCustomers() {
		System.out.println("List of customers");
		for ( Customer customer: customers ) {
			printCustomerInfo(customer);
		}
		System.out.println("End of list");
	}

	public void getCustomerReport() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			String result = foundCustomer.getReport() ;
			System.out.println(result);
		}
	}

	public void rentVideo() {
		Customer foundCustomer = getCustomerByUserInput();

		System.out.println("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		Video foundVideo = null ;
		for ( Video video: videos ) {
			if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
				foundVideo = video ;
				break ;
			}
		}

		if ( foundVideo == null ) return ;

		Rental rental = new Rental(foundVideo) ;
		foundVideo.setRented(true);

		// encapsulate collection
		List<Rental> customerRentals = foundCustomer.getRentals() ;
		customerRentals.add(rental);
		foundCustomer.setRentals(customerRentals);
	}

	// SRP
	// replace parameter with explicit method
	public void register(String object) {
		//별도 메소드로
		if ( object.equals("customer") ) {
			System.out.println("Enter customer name: ") ;
			String name = scanner.next();
			Customer customer = new Customer(name) ;
			customers.add(customer) ;
		}
		else {

			System.out.println("Enter video title to register: ") ;
			String title = scanner.next() ;

			System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
			int videoType = scanner.nextInt();

			System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
			int priceCode = scanner.nextInt();

			Video video = VideoFactory.createVideo(title,priceCode, videoType) ;
			videos.add(video) ;
		}
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt() ;

		return command ;

	}
}
