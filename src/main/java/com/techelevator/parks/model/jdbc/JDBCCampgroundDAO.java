package com.techelevator.parks.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.CampgroundDAO;
import com.techelevator.parks.model.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
    public List<Campground> getAllCampgrounds(Park selectedPark) {
        String campgroundAtPark = "SELECT * FROM campground WHERE park_id = ? ";
        ArrayList<Campground> campgrounds = new ArrayList<Campground>();
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(campgroundAtPark, selectedPark.getId());
        while(rowset.next()) {
            Campground c = new Campground();
            c.setCampgroundId(rowset.getLong("campground_id"));
            c.setParkId(selectedPark.getId());
            c.setName(rowset.getString("name"));
            c.setOpenDate(rowset.getString("open_from_mm"));
            c.setCloseDate(rowset.getString("open_to_mm"));
            c.setFee(rowset.getBigDecimal("daily_fee"));
            campgrounds.add(c);
        }
        return campgrounds;
    }

}