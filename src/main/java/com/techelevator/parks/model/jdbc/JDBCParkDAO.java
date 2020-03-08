package com.techelevator.parks.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO {

private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {

		String allParks = "SELECT * FROM park ORDER BY name ASC";
		
		ArrayList<Park> parks = new ArrayList<Park>();
		
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(allParks);
		
		while(rowset.next()) {
			Park p = new Park();
			p.setId(rowset.getLong("park_id"));
			p.setName(rowset.getString("name"));
			p.setLocation(rowset.getString("location"));
			p.setEstablishedDate(rowset.getDate("establish_date").toLocalDate());
			p.setArea(rowset.getInt("area"));
			p.setAnnualVisitors(rowset.getInt("visitors"));
			p.setDescription(rowset.getString("description"));
			parks.add(p);			
		}		
		return parks;
	}
}


