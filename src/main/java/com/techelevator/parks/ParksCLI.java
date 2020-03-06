package com.techelevator.parks;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.parks.model.CampgroundDAO;
import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.ParkDAO;
import com.techelevator.parks.model.ReservationDAO;
import com.techelevator.parks.model.SiteDAO;
import com.techelevator.parks.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.parks.model.jdbc.JDBCParkDAO;
import com.techelevator.parks.model.jdbc.JDBCReservationDAO;
import com.techelevator.parks.model.jdbc.JDBCSiteDAO;
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
		
		// QUERY AND DISPLAY ALL PARKS TO USER
		// TODO ADD A QUIT OPTION TO THIS FIRST MENU
		List<Park> parks = parkDAO.getAllParks();
		
		// SOLICIT USER FOR PARK
		Park selectedPark = (Park) menu.getChoiceFromOptions(parks.toArray());
	}
}
