package com.techelevator.parks.model.jdbc;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.techelevator.parks.model.Park;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {

	private static final long TEST_PARK_1_ID = 1000000;
	private static final long TEST_PARK_2_ID = 2000000;
	private static final long TEST_PARK_3_ID = 3000000;
	private static final String TEST_PARK_1_NAME = "Jurassic Park";
	private static final String TEST_PARK_2_NAME = "Triassic Park";
	private static final String TEST_PARK_3_NAME = "Cretaceous Park";
	private static final String TEST_PARK_1_LOCATION = "Isla Nublar";
	private static final String TEST_PARK_2_LOCATION = "Isla Sorna";
	private static final String TEST_PARK_3_LOCATION = "South Bend";
	private static final LocalDate TEST_PARK_1_ESTABLISHED_DATE = LocalDate.parse("1996-07-04");
	private static final LocalDate TEST_PARK_2_ESTABLISHED_DATE = LocalDate.parse("1987-02-17");
	private static final LocalDate TEST_PARK_3_ESTABLISHED_DATE = LocalDate.parse("1950-01-16");
	private static final int TEST_PARK_1_AREA = 1000;
	private static final int TEST_PARK_2_AREA = 2000;
	private static final int TEST_PARK_3_AREA = 3000;
	private static final int TEST_PARK_1_ANNUAL_VISITORS = 4000;
	private static final int TEST_PARK_2_ANNUAL_VISITORS = 5000;
	private static final int TEST_PARK_3_ANNUAL_VISITORS = 6000;
	private static final String TEST_PARK_1_DESCRIPTION = "You've seen the movie, you know what happens here";
	private static final String TEST_PARK_2_DESCRIPTION = "It's like Jurassic Park, except it's Triassic";
	private static final String TEST_PARK_3_DESCRIPTION = "It's like Jurassic Park, except it's Cretaceous";
	
	private JDBCParkDAO dao;
	
	private Park newPark(long id, String name, String location, LocalDate establishedDate, int area, int annualVisitors, String description) {
		Park thePark = new Park();
		thePark.setId(id);
		thePark.setName(name);
		thePark.setLocation(location);
		thePark.setEstablishedDate(establishedDate);
		thePark.setArea(area);
		thePark.setAnnualVisitors(annualVisitors);
		thePark.setDescription(description);
		return thePark;
	}
	
	private void assertParksAreEqual(Park expected, Park actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getLocation(), actual.getLocation());
		assertEquals(expected.getEstablishedDate(), actual.getEstablishedDate());
		assertEquals(expected.getArea(), actual.getArea());
		assertEquals(expected.getAnnualVisitors(), actual.getAnnualVisitors());
		assertEquals(expected.getDescription(), actual.getDescription());
	}
	
	@Before
	public void setup() {
		String sqlInsertPark = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sqlDeleteReservations = "DELETE FROM reservation";
		String sqlDeleteSites = "DELETE FROM site";
		String sqlDeleteCampgrounds = "DELETE FROM campground";
		String sqlDeleteParks = "DELETE FROM park";
		jdbcTemplate.update(sqlDeleteReservations);
		jdbcTemplate.update(sqlDeleteSites);
		jdbcTemplate.update(sqlDeleteCampgrounds);
		jdbcTemplate.update(sqlDeleteParks);
		jdbcTemplate.update(sqlInsertPark, TEST_PARK_1_ID, TEST_PARK_1_NAME, TEST_PARK_1_LOCATION, TEST_PARK_1_ESTABLISHED_DATE, TEST_PARK_1_AREA, TEST_PARK_1_ANNUAL_VISITORS, TEST_PARK_1_DESCRIPTION);
		jdbcTemplate.update(sqlInsertPark, TEST_PARK_2_ID, TEST_PARK_2_NAME, TEST_PARK_2_LOCATION, TEST_PARK_2_ESTABLISHED_DATE, TEST_PARK_2_AREA, TEST_PARK_2_ANNUAL_VISITORS, TEST_PARK_2_DESCRIPTION);
		jdbcTemplate.update(sqlInsertPark, TEST_PARK_3_ID, TEST_PARK_3_NAME, TEST_PARK_3_LOCATION, TEST_PARK_3_ESTABLISHED_DATE, TEST_PARK_3_AREA, TEST_PARK_3_ANNUAL_VISITORS, TEST_PARK_3_DESCRIPTION);
		dao = new JDBCParkDAO(getDataSource());
	}
	
	@Test
	public void return_all_parks() {
		Park park1 = newPark(TEST_PARK_1_ID, TEST_PARK_1_NAME, TEST_PARK_1_LOCATION, TEST_PARK_1_ESTABLISHED_DATE, TEST_PARK_1_AREA, TEST_PARK_1_ANNUAL_VISITORS, TEST_PARK_1_DESCRIPTION);
		Park park2 = newPark(TEST_PARK_2_ID, TEST_PARK_2_NAME, TEST_PARK_2_LOCATION, TEST_PARK_2_ESTABLISHED_DATE, TEST_PARK_2_AREA, TEST_PARK_2_ANNUAL_VISITORS, TEST_PARK_2_DESCRIPTION);
		Park park3 = newPark(TEST_PARK_3_ID, TEST_PARK_3_NAME, TEST_PARK_3_LOCATION, TEST_PARK_3_ESTABLISHED_DATE, TEST_PARK_3_AREA, TEST_PARK_3_ANNUAL_VISITORS, TEST_PARK_3_DESCRIPTION);
		
		List<Park> actual = dao.getAllParks();
		
		assertEquals(3, actual.size());
		assertParksAreEqual(park1, actual.get(0));
		assertParksAreEqual(park2, actual.get(1));
		assertParksAreEqual(park3, actual.get(2));
	}
}