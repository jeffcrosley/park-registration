package com.techelevator.parks;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.CampgroundDAO;
import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.ParkDAO;
import com.techelevator.parks.model.ReservationDAO;
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
	
	public void parkMenuLoop(Park selectedPark) {
		boolean parkMenuLoop = true;
		do {
			// DISPLAY PARK INFO
			Display.printParkInfo(selectedPark);
			
			// GET USER CHOICE FROM PARK MENU
			String parkMenuSelection = (String) menu.getChoiceFromOptions(Display.getParkMenu().toArray());
			
			if (parkMenuSelection == Display.getParkMenu1()) {
				campgroundMenuLoop(selectedPark);
			} else if (parkMenuSelection == Display.getParkMenu2()) {
				// TODO SEARCH FOR RESERVATION
			} else if (parkMenuSelection == Display.getParkMenu3()) {
				parkMenuLoop = false;
			} else {
				// TODO ADD DEFAULT TO PROMPT USER FOR A VALID SELECTION
			}
		} while (parkMenuLoop);		
	}
	
	public void campgroundMenuLoop(Park selectedPark) {
		List<Campground> selectedParkCampgrounds = campgroundDAO.getCampgrounds(selectedPark);
	}
}
