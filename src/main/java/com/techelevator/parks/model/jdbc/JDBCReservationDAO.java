package com.techelevator.parks.model.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.parks.model.Reservation;
import com.techelevator.parks.model.ReservationDAO;
import com.techelevator.parks.model.Site;

public class JDBCReservationDAO implements ReservationDAO {
		
	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	void createReservation() {
		
	}
	
	@Override
	public Reservation createReservation(Site selectedSite, String reservationName, LocalDate arrivalDate,
			LocalDate departureDate) {
		String searchForReservation = "SELECT * FROM site where " + 
				"campground_id = ? AND " + 
				"site_id NOT IN " + 
				"(SELECT DISTINCT site.site_id FROM site " + 
				"JOIN reservation ON site.site_id = reservation.site_id " + 
				"WHERE " + 
				"reservation.from_date BETWEEN '?' AND '?' " + 
				"OR reservation.to_date BETWEEN '2020-03-04' AND '2020-03-08' " + 
				"OR '2020-03-04' BETWEEN reservation.from_date AND reservation.to_date " + 
				"OR '2020-03-08' BETWEEN reservation.from_date AND reservation.to_date) ";
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(searchForReservation);
		
		return null;
	}

}
