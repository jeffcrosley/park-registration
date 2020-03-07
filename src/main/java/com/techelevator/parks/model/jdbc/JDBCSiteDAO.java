package com.techelevator.parks.model.jdbc;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.Site;
import com.techelevator.parks.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getAvailableSites(Campground selectedCampground, LocalDate desiredArrivalDate,
			LocalDate desiredDepartureDate) {
		// TODO Auto-generated method stub
		return null;
	}

	private String convertDateToMonthNumber(LocalDate date) {
		
		String output = date.getMonth().toString();
		
		return output;
		
	}

}
