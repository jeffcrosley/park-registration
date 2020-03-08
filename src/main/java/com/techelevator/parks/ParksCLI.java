package com.techelevator.parks;

import java.time.LocalDate;
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
			System.out.println(Display.getSelectParkPrompt());
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
			System.out.println(Display.getSelectCommandPrompt());
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
			List<Campground> campgrounds = campgroundDAO.getAllCampgrounds(selectedPark);
			Display.printCampgrounds(selectedPark, campgrounds);
			
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
		System.out.println(Display.getDivider());
		System.out.println("\t" + Display.getCampgroundsHeader());
		Campground selectedCampground = (Campground) menu.getChoiceFromOptions(campgrounds.toArray());
		
		// GET ARRIVAL AND DEPARTURES DATES
		LocalDate arrivalDate = menu.getDateFromUserInput(Display.getArrivalDatePrompt());
		LocalDate departureDate = menu.getDateFromUserInput(Display.getDepartureDatePrompt());
		
		// CHECK FOR AVAILABLE SITES
		List<Site> availableSites = siteDAO.getAvailableSites(selectedCampground, arrivalDate, departureDate);
		
		if (availableSites.size() == 0) {
			// TODO RE-PROMPT IF THERE ARE NO AVAILABLE SITES; WILL PROBABLY NEED TO WRAP THE ABOVE CODE IN A WHILE LOOP
		} else {
			makeReservation(selectedCampground, availableSites, arrivalDate, departureDate);
		}
	}
	
	private void makeReservation(Campground selectedCampground, List<Site> availableSites, LocalDate arrivalDate, LocalDate departureDate) {
		
		// GET SITE SELECTION
		System.out.println(Display.getDivider());
		System.out.println("\t" + Display.getSitesHeader());
		// TODO FIX TO DISPLAY THE "TOP 5" SITES BY SOME CRITERIA OR OTHER
		Site selectedSite = (Site) menu.getSiteChoiceFromOptions(selectedCampground, availableSites, arrivalDate, departureDate);
		
		// GET RESERVATION NAME
		String reservationName = menu.getStringFromUserInput(Display.getReservationNamePrompt());
		
		// CREATE RESERVATION AND DISPLAY ID TO USER
		 Reservation reservation = reservationDAO.createReservation(selectedSite, reservationName, arrivalDate, departureDate);
		 System.out.println(Display.getReservationMade() + reservation.getId());
		 
		 // TODO FIX PROGRAM END BEHAVIOR
	}
}
