package com.techelevator.parks.model.jdbc;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.parks.model.Reservation;
import com.techelevator.parks.model.ReservationDAO;
import com.techelevator.parks.model.Site;

public class JDBCReservationDAO implements ReservationDAO {
		
	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Reservation createReservation(Site selectedSite, String reservationName, Date arrivalDate,
			Date departureDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
