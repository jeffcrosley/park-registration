package com.techelevator.parks.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.Site;
import com.techelevator.parks.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	static final String searchForReservation = "SELECT * FROM site where " + 
			"campground_id = '?' AND " + 
			"site_id NOT IN " + 
			"(SELECT DISTINCT site.site_id FROM site " + 
			"JOIN reservation ON site.site_id = reservation.site_id " + 
			"WHERE " + 
			"reservation.from_date BETWEEN ? AND ? " + 
			"OR reservation.to_date BETWEEN ? AND ? " + 
			"OR ? BETWEEN reservation.from_date AND reservation.to_date " + 
			"OR ? BETWEEN reservation.from_date AND reservation.to_date) ";
	@Override
	public List<Site> getAvailableSites(Campground selectedCampground, LocalDate desiredArrivalDate,
			LocalDate desiredDepartureDate) {
	String happyPlace = "SELECT * FROM site where " +
			"campground_id = '?' AND site_id NOT IN " +
			"(SELECT DISTINCT site.site_id FROM site " +
			"JOIN reservation ON site.site_id = reservation.site_id " +
			"WHERE reservation.from_date BETWEEN '?' AND '?') ";
		ArrayList<Site> availSites = new ArrayList<Site>();
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(happyPlace, selectedCampground, desiredArrivalDate, desiredDepartureDate);
		while(rowset.next()) {
			Site s = new Site();
			s.setId(rowset.getLong("site_id"));
			s.setMaxOccupancy(rowset.getLong("max_occupancy"));
			s.setAccessible(rowset.getBoolean("accessible"));
			s.setMaxRVLength(rowset.getLong("max_rv_length"));
			s.setHasUtilities(rowset.getBoolean("utilities"));
			
		}
		return availSites;
	}
	

	private String convertDateToMonthNumber(LocalDate date) {
		
		String output = date.getMonth().toString();
		
		return output;
		
	}

}
