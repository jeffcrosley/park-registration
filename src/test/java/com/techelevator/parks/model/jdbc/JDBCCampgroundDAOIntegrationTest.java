package com.techelevator.parks.model.jdbc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.Park;

public class JDBCCampgroundDAOIntegrationTest extends DAOIntegrationTest {

private static final long TEST_PARK_1_ID = 1000000;
private static final long TEST_PARK_2_ID = 2000000;
private static final String TEST_PARK_1_NAME = "Jurassic Park";
private static final String TEST_PARK_2_NAME = "Triassic Park";
private static final String TEST_PARK_1_LOCATION = "Isla Nublar";
private static final String TEST_PARK_2_LOCATION = "Isla Sorna";
private static final LocalDate TEST_PARK_1_ESTABLISHED_DATE = LocalDate.parse("1996-07-04");
private static final LocalDate TEST_PARK_2_ESTABLISHED_DATE = LocalDate.parse("1987-02-17");
private static final int TEST_PARK_1_AREA = 1000;
private static final int TEST_PARK_2_AREA = 2000;
private static final int TEST_PARK_1_ANNUAL_VISITORS = 4000;
private static final int TEST_PARK_2_ANNUAL_VISITORS = 5000;
private static final String TEST_PARK_1_DESCRIPTION = "You've seen the movie, you know what happens here";
private static final String TEST_PARK_2_DESCRIPTION = "It's like Jurassic Park, except it's Triassic";
	
	
private static final long TEST_CAMPGROUND_1_ID = 1000000;
private static final String TEST_CAMPGROUND_1_NAME = "CG1";
private static final String TEST_CAMPGROUND_1_OPEN_DATE = "01";
private static final String TEST_CAMPGROUND_1_CLOSE_DATE = "07";
private static final BigDecimal TEST_CAMPGROUND_1_FEE = new BigDecimal("10.0");
private static final long TEST_CAMPGROUND_2_ID = 2000000;
private static final String TEST_CAMPGROUND_2_NAME = "CG2";
private static final String TEST_CAMPGROUND_2_OPEN_DATE = "04";
private static final String TEST_CAMPGROUND_2_CLOSE_DATE = "10";
private static final BigDecimal TEST_CAMPGROUND_2_FEE = new BigDecimal("20.0");
private static final long TEST_CAMPGROUND_3_ID = 3000000;
private static final String TEST_CAMPGROUND_3_NAME = "CG3";
private static final String TEST_CAMPGROUND_3_OPEN_DATE = "03";
private static final String TEST_CAMPGROUND_3_CLOSE_DATE = "07";
private static final BigDecimal TEST_CAMPGROUND_3_FEE = new BigDecimal("30.0");


	
private JDBCCampgroundDAO dao;
	
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

	private Campground newCampground(long campgroundId, long parkId, String name, String openDate, String closeDate, BigDecimal fee) {
		Campground theCampground = new Campground();
		theCampground.setCampgroundId(campgroundId);
		theCampground.setParkId(parkId);;
		theCampground.setName(name);
		theCampground.setOpenDate(openDate);
		theCampground.setCloseDate(closeDate);
		theCampground.setFee(fee);
		
		return theCampground;
	}
	
	private void assertCampgroundsAreEqual(Campground expected, Campground actual) {
		assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
		assertEquals(expected.getParkId(), actual.getParkId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getOpenDate(), actual.getOpenDate());
		assertEquals(expected.getCloseDate(), actual.getCloseDate());
		assertEquals(expected.getFee(), actual.getFee());
	}
	
	@Before
	public void setup() {
		String sqlInsertPark = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sqlInsertCampground= "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (?, ?, ?, ?, ?, ?)";
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
		jdbcTemplate.update(sqlInsertCampground, TEST_CAMPGROUND_1_ID, TEST_PARK_1_ID, TEST_CAMPGROUND_1_NAME, TEST_CAMPGROUND_1_OPEN_DATE, TEST_CAMPGROUND_1_CLOSE_DATE, TEST_CAMPGROUND_1_FEE);
 		jdbcTemplate.update(sqlInsertCampground, TEST_CAMPGROUND_2_ID, TEST_PARK_2_ID, TEST_CAMPGROUND_2_NAME, TEST_CAMPGROUND_2_OPEN_DATE, TEST_CAMPGROUND_2_CLOSE_DATE, TEST_CAMPGROUND_2_FEE);
		jdbcTemplate.update(sqlInsertCampground, TEST_CAMPGROUND_3_ID, TEST_PARK_2_ID, TEST_CAMPGROUND_3_NAME, TEST_CAMPGROUND_3_OPEN_DATE, TEST_CAMPGROUND_3_CLOSE_DATE, TEST_CAMPGROUND_3_FEE);
		dao = new JDBCCampgroundDAO(getDataSource());
	}
	
	@Test
	public void return_all_campgrounds() {

		Park park2 = newPark(TEST_PARK_2_ID, TEST_PARK_2_NAME, TEST_PARK_2_LOCATION, TEST_PARK_2_ESTABLISHED_DATE, TEST_PARK_2_AREA, TEST_PARK_2_ANNUAL_VISITORS, TEST_PARK_2_DESCRIPTION);
		Campground campground2 = newCampground(TEST_CAMPGROUND_2_ID, TEST_PARK_2_ID, TEST_CAMPGROUND_2_NAME, TEST_CAMPGROUND_2_OPEN_DATE, TEST_CAMPGROUND_2_CLOSE_DATE, TEST_CAMPGROUND_2_FEE);
		Campground campground3 = newCampground(TEST_CAMPGROUND_3_ID, TEST_PARK_2_ID, TEST_CAMPGROUND_3_NAME, TEST_CAMPGROUND_3_OPEN_DATE, TEST_CAMPGROUND_3_CLOSE_DATE, TEST_CAMPGROUND_3_FEE);
		
		List<Campground> actual = dao.getAllCampgrounds(park2);
		
		assertEquals(2, actual.size());
		
		assertCampgroundsAreEqual(campground2, actual.get(0));
		assertCampgroundsAreEqual(campground3, actual.get(1));
		
	}

}