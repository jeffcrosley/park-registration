package com.techelevator.parks.model;

import java.util.Date;
import java.util.List;

public interface SiteDAO {
	public List<Site> getAvailableSites(Campground selectedCampground, Date desiredArrivalDate, Date desiredDepartureDate);
}
