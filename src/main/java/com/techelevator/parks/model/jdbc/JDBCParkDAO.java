package com.techelevator.parks.model.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO {

private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		// TODO Auto-generated method stub
		return null;
	}

}
