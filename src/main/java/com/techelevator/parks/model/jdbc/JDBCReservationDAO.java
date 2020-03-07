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

				return null;
	}





	}

