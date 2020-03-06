package com.techelevator.parks;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.CampgroundDAO;
import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.ParkDAO;
import com.techelevator.parks.model.Reservation;
import com.techelevator.parks.model.ReservationDAO;
import com.techelevator.parks.model.Site;
import com.techelevator.parks.model.SiteDAO;
import com.techelevator.parks.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.parks.model.jdbc.JDBCParkDAO;
import com.techelevator.parks.model.jdbc.JDBCReservationDAO;
import com.techelevator.parks.model.jdbc.JDBCSiteDAO;
import com.techelevator.parks.view.Display;
import com.techelevator.parks.view.Menu;

public class ParksCLI {

	private Menu menu;	
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/parks");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ParksCLI application = new ParksCLI(dataSource);
		application.run();
	}

	public ParksCLI(DataSource datasource) {
		parkDAO = new JDBCParkDAO(datasource);
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		siteDAO = new JDBCSiteDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}

	public void run() {
		
		// INSTANTIATE MENU
		menu = new Menu(System.in, System.out);
		
		// MAIN LOOP
		// QUERY AND DISPLAY ALL PARKS TO USER
		boolean mainLoop = true;
		do {
			List<Park> parks = parkDAO.getAllParks();
			Park quit = new Park();
			String quitProgram = "Quit Program";
			quit.setName(quitProgram);
			parks.add(quit);
			
			// SOLICIT USER FOR PARK (OR QUIT)
			Park selectedPark = (Park) menu.getChoiceFromOptions(parks.toArray());
			
			// TEST FOR QUIT CONDITION AND END PROGRAM IF TRUE
			if (selectedPark.getName() == quitProgram) {
				mainLoop = false;
			} else {				
				// PARK MENU LOOP
				parkMenuLoop(selectedPark);
			}
			
		} while (mainLoop);
		
	}		
	
	private void parkMenuLoop(Park selectedPark) {
		boolean parkMenuLoop = true;
		do {
			// DISPLAY PARK INFO
			Display.printParkInfo(selectedPark);
			
			// GET PARK MENU SELECTION
			String parkMenuSelection = (String) menu.getChoiceFromOptions(Display.getParkMenu().toArray());
			
			if (parkMenuSelection == Display.getParkMenu1()) {
				campgroundMenuLoop(selectedPark);
			} else if (parkMenuSelection == Display.getParkMenu2()) {
				//TODO CREATE PARK-WIDE RESERVATION METHOD (BONUS)
			} else {
				parkMenuLoop = false;
			}
		} while (parkMenuLoop);		
	}
	
	private void campgroundMenuLoop(Park selectedPark) {
		boolean campgroundMenuLoop = true;
		do {
			// DISPLAY CAMPGROUNDS
			// TODO JEFF: INTEGRATION TEST
			List<Campground> campgrounds = campgroundDAO.getAllCampgrounds(selectedPark);
			// TODO JAKE: CREATE THIS METHOD; MAKE A toString OVERRIDE ON Campground;
			// 	CREATE METHOD IN Display THAT PRINTS EACH Campground IN selectedPark
			Display.printCampgrounds(campgrounds);
			
			// GET CAMPGROUND MENU SELECTION
			String campgroundMenuSelection = (String) menu.getChoiceFromOptions(Display.getCampgroundMenu().toArray());
			
			if (campgroundMenuSelection == Display.getCampgroundMenu1()) {
				searchForAvailableReservation(campgrounds);
			} else {
				campgroundMenuLoop = false;
			}
		} while (campgroundMenuLoop);
	}
	
	private void searchForAvailableReservation(List<Campground> campgrounds) {
		
		// GET CAMPGROUND SELECTION
		Campground selectedCampground = (Campground) menu.getChoiceFromOptions(campgrounds.toArray());
		
		// GET ARRIVAL AND DEPARTURES DATES
		Date arrivalDate = menu.getDateFromUserInput(Display.getArrivalDatePrompt());
		Date departureDate = menu.getDateFromUserInput(Display.getDepartureDatePrompt());
		
		// CHECK FOR AVAILABLE SITES
		// TODO JAKE: CREATE getAvailableSites() IN JDBCSiteDAO
		//	Note: Limit this to 5 (by id ASC)
		// 	Note: The total cost will have to be derived from the Campground rate and the dates
		// TODO JEFF: INTEGRATION TEST
		List<Site> availableSites = siteDAO.getAvailableSites(selectedCampground, arrivalDate, departureDate);
		
		if (availableSites.size() == 0) {
			// TODO RE-PROMPT IF THERE ARE NO AVAILABLE SITES; WILL PROBABLY NEED TO WRAP THE ABOVE CODE IN A WHILE LOOP
		} else {
			makeReservation(availableSites, arrivalDate, departureDate);
		}
	}
	
	private void makeReservation(List<Site> availableSites, Date arrivalDate, Date departureDate) {
		
		// GET SITE SELECTION
		// TODO FIX toString ON Site SO THIS ALL FORMATS CORRECTLY
		// TODO FIX menu SO IT TAKES/DISPLAYS A VARIABLE PROMPT, DEPENDING ON CONTEXT
		Site selectedSite = (Site) menu.getChoiceFromOptions(availableSites.toArray());
		
		// GET RESERVATION NAME
		String reservationName = menu.getStringFromUserInput(Display.getReservationNamePrompt());
		
		// CREATE RESERVATION AND DISPLAY ID TO USER
		// TODO JAKE: CREATE createReservation() METHOD IN JDBCreservationDAO
		// 	NOTE: THE RESERVATION CREATION METHOD NEEDS TO INCLUDE THE CREATE DATE
		// TODO: JEFF: TEST THIS
		 Reservation reservation = reservationDAO.createReservation(selectedSite, reservationName, arrivalDate, departureDate);
		 System.out.println(Display.getReservationMade() + reservation.getId());
	}
}
