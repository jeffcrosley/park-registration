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

	public Reservation createReservation(Site selectedSite, String reservationName, LocalDate arrivalDate, LocalDate departureDate) {
		
		Reservation output = new Reservation();
		
		String sqlMakeReservation = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date) VALUES (?, ?, ?, ?, ?) RETURNING reservation_id";

		long reservationId = jdbcTemplate.queryForObject(sqlMakeReservation, Long.class, selectedSite.getId(), reservationName, arrivalDate, departureDate, LocalDate.now());
		
		output.setId(reservationId);
		output.setSiteId(selectedSite.getId());
		output.setName(reservationName);
		output.setFromDate(arrivalDate);
		output.setToDate(departureDate);
		output.setCreateDate(LocalDate.now());
		
		return output;
		
	}

}

