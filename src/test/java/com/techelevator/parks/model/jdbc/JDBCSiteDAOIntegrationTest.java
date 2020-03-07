package com.techelevator.parks.model.jdbc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.Site;

public class JDBCSiteDAOIntegrationTest extends DAOIntegrationTest {

	private static final long TEST_PARK_1_ID = 1000000;
	private static final String TEST_PARK_1_NAME = "Jurassic Park";
	private static final String TEST_PARK_1_LOCATION = "Isla Nublar";
	private static final LocalDate TEST_PARK_1_ESTABLISHED_DATE = LocalDate.parse("1996-07-04");
	private static final int TEST_PARK_1_AREA = 1000;
	private static final int TEST_PARK_1_ANNUAL_VISITORS = 4000;
	private static final String TEST_PARK_1_DESCRIPTION = "You've seen the movie, you know what happens here";
	private static final long TEST_PARK_2_ID = 2000000;
	private static final String TEST_PARK_2_NAME = "Triassic Park";
	private static final String TEST_PARK_2_LOCATION = "Isla Sorna";
	private static final LocalDate TEST_PARK_2_ESTABLISHED_DATE = LocalDate.parse("1987-02-17");
	private static final int TEST_PARK_2_AREA = 2000;
	private static final int TEST_PARK_2_ANNUAL_VISITORS = 5000;
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

	private static final long TEST_SITE_1_ID = 1000000;
	private static final long TEST_SITE_1_NUMBER = 110000;
	private static final long TEST_SITE_1_MAX_OCCUPANCY = 111000;
	private static final boolean TEST_SITE_1_IS_ACCESSIBLE = true;
	private static final long TEST_SITE_1_MAX_RV_LENGTH = 111100;
	private static final boolean TEST_SITE_1_HAS_UTILITIES = false;	
	private static final long TEST_SITE_2_ID = 2000000;
	private static final long TEST_SITE_2_NUMBER = 220000;
	private static final long TEST_SITE_2_MAX_OCCUPANCY = 222000;
	private static final boolean TEST_SITE_2_IS_ACCESSIBLE = false;
	private static final long TEST_SITE_2_MAX_RV_LENGTH = 222200;
	private static final boolean TEST_SITE_2_HAS_UTILITIES = true;	
	private static final long TEST_SITE_3_ID = 3000000;
	private static final long TEST_SITE_3_NUMBER = 330000;
	private static final long TEST_SITE_3_MAX_OCCUPANCY = 333000;
	private static final boolean TEST_SITE_3_IS_ACCESSIBLE = true;
	private static final long TEST_SITE_3_MAX_RV_LENGTH = 333300;
	private static final boolean TEST_SITE_3_HAS_UTILITIES = true;
	private static final long TEST_SITE_4_ID = 4000000;
	private static final long TEST_SITE_4_NUMBER = 440000;
	private static final long TEST_SITE_4_MAX_OCCUPANCY = 444000;
	private static final boolean TEST_SITE_4_IS_ACCESSIBLE = false;
	private static final long TEST_SITE_4_MAX_RV_LENGTH = 444400;
	private static final boolean TEST_SITE_4_HAS_UTILITIES = false;
	private static final long TEST_SITE_5_ID = 5000000;
	private static final long TEST_SITE_5_NUMBER = 550000;
	private static final long TEST_SITE_5_MAX_OCCUPANCY = 555000;
	private static final boolean TEST_SITE_5_IS_ACCESSIBLE = true;
	private static final long TEST_SITE_5_MAX_RV_LENGTH = 555500;
	private static final boolean TEST_SITE_5_HAS_UTILITIES = true;
	private static final long TEST_SITE_6_ID = 6000000;
	private static final long TEST_SITE_6_NUMBER = 660000;
	private static final long TEST_SITE_6_MAX_OCCUPANCY = 666000;
	private static final boolean TEST_SITE_6_IS_ACCESSIBLE = false;
	private static final long TEST_SITE_6_MAX_RV_LENGTH = 666600;
	private static final boolean TEST_SITE_6_HAS_UTILITIES = false;

	private static final long TEST_RESERVATION_1_ID = 1000000;
	private static final String TEST_RESERVATION_1_NAME = "Test Reservation";
	private static final LocalDate TEST_RESERVATION_1_FROM_DATE = LocalDate.of(2020, Month.APRIL, 15);
	private static final LocalDate TEST_RESERVATION_1_TO_DATE = LocalDate.of(2020, Month.JULY, 15);
	private static final LocalDate TEST_RESERVATION_1_CREATE_DATE = LocalDate.of(2019, Month.DECEMBER, 15);
		
	private JDBCSiteDAO dao;
		
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
		
		private Site newSite(long id, long campgroundId, long site_number, long maxOccupancy, boolean isAccessible, long maxRVLength, boolean hasUtilities) {
			Site theSite = new Site();
			theSite.setId(id);
			theSite.setCampgroundId(campgroundId);
			theSite.setSite_number(site_number);
			theSite.setMaxOccupancy(maxOccupancy);
			theSite.setAccessible(isAccessible);
			theSite.setMaxRVLength(maxRVLength);
			theSite.setHasUtilities(hasUtilities);
			return theSite;
		}
		
		private void assertSitesAreEqual(Site expected, Site actual) {
			assertEquals(expected.getId(), actual.getId());
			assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
			assertEquals(expected.getSite_number(), actual.getSite_number());
			assertEquals(expected.getMaxOccupancy(), actual.getMaxOccupancy());
			assertEquals(expected.isAccessible(), actual.isAccessible());
			assertEquals(expected.getMaxRVLength(), actual.getMaxRVLength());
			assertEquals(expected.isHasUtilities(), actual.isHasUtilities());
		}
		
		@Before
		public void setup() {
			String sqlInsertPark = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
			String sqlInsertCampground = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (?, ?, ?, ?, ?, ?)";
			String sqlInsertSite = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) VALUES (?, ?, ?, ?, ?, ?, ?)";
			String sqlInsertReservation = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) VALUES (?, ?, ?, ?, ?, ?)";
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
			jdbcTemplate.update(sqlInsertSite, TEST_SITE_1_ID, TEST_CAMPGROUND_1_ID, TEST_SITE_1_NUMBER, TEST_SITE_1_MAX_OCCUPANCY, TEST_SITE_1_IS_ACCESSIBLE, TEST_SITE_1_MAX_RV_LENGTH, TEST_SITE_1_HAS_UTILITIES); 
			jdbcTemplate.update(sqlInsertSite, TEST_SITE_2_ID, TEST_CAMPGROUND_1_ID, TEST_SITE_2_NUMBER, TEST_SITE_2_MAX_OCCUPANCY, TEST_SITE_2_IS_ACCESSIBLE, TEST_SITE_2_MAX_RV_LENGTH, TEST_SITE_2_HAS_UTILITIES); 
			jdbcTemplate.update(sqlInsertSite, TEST_SITE_3_ID, TEST_CAMPGROUND_2_ID, TEST_SITE_3_NUMBER, TEST_SITE_3_MAX_OCCUPANCY, TEST_SITE_3_IS_ACCESSIBLE, TEST_SITE_3_MAX_RV_LENGTH, TEST_SITE_3_HAS_UTILITIES); 
			jdbcTemplate.update(sqlInsertSite, TEST_SITE_4_ID, TEST_CAMPGROUND_2_ID, TEST_SITE_4_NUMBER, TEST_SITE_4_MAX_OCCUPANCY, TEST_SITE_4_IS_ACCESSIBLE, TEST_SITE_4_MAX_RV_LENGTH, TEST_SITE_4_HAS_UTILITIES); 
			jdbcTemplate.update(sqlInsertSite, TEST_SITE_5_ID, TEST_CAMPGROUND_2_ID, TEST_SITE_5_NUMBER, TEST_SITE_5_MAX_OCCUPANCY, TEST_SITE_5_IS_ACCESSIBLE, TEST_SITE_5_MAX_RV_LENGTH, TEST_SITE_5_HAS_UTILITIES); 
			jdbcTemplate.update(sqlInsertSite, TEST_SITE_6_ID, TEST_CAMPGROUND_3_ID, TEST_SITE_6_NUMBER, TEST_SITE_6_MAX_OCCUPANCY, TEST_SITE_6_IS_ACCESSIBLE, TEST_SITE_6_MAX_RV_LENGTH, TEST_SITE_6_HAS_UTILITIES);
			jdbcTemplate.update(sqlInsertReservation, TEST_RESERVATION_1_ID, TEST_SITE_5_ID, TEST_RESERVATION_1_NAME, TEST_RESERVATION_1_FROM_DATE, TEST_RESERVATION_1_TO_DATE, TEST_RESERVATION_1_CREATE_DATE);
			dao = new JDBCSiteDAO(getDataSource());
		}		

		@Test
		public void return_available_sites() {
			
			Campground campground2 = newCampground(TEST_CAMPGROUND_2_ID, TEST_PARK_2_ID, TEST_CAMPGROUND_2_NAME, TEST_CAMPGROUND_2_OPEN_DATE, TEST_CAMPGROUND_2_CLOSE_DATE, TEST_CAMPGROUND_2_FEE);
			Site site3 = newSite(TEST_SITE_3_ID, TEST_CAMPGROUND_2_ID, TEST_SITE_3_NUMBER, TEST_SITE_3_MAX_OCCUPANCY, TEST_SITE_3_IS_ACCESSIBLE, TEST_SITE_3_MAX_RV_LENGTH, TEST_SITE_3_HAS_UTILITIES);
			Site site4 = newSite(TEST_SITE_4_ID, TEST_CAMPGROUND_2_ID, TEST_SITE_4_NUMBER, TEST_SITE_4_MAX_OCCUPANCY, TEST_SITE_4_IS_ACCESSIBLE, TEST_SITE_4_MAX_RV_LENGTH, TEST_SITE_4_HAS_UTILITIES);
			
			final LocalDate DESIRED_ARRIVAL_DATE = LocalDate.of(2020, Month.MAY, 15);
			final LocalDate DESIRED_DEPARTURE_DATE = LocalDate.of(2020, Month.SEPTEMBER, 15);
			
			List<Site> actual = dao.getAvailableSites(campground2, DESIRED_ARRIVAL_DATE, DESIRED_DEPARTURE_DATE);

			assertEquals(2, actual.size());
			assertSitesAreEqual(site3, actual.get(0));
			assertSitesAreEqual(site4, actual.get(1));
	
		}


}
