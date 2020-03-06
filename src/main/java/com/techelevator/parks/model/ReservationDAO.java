package com.techelevator.parks.model;

import java.util.Date;

public interface ReservationDAO {
	public Reservation createReservation(Site selectedSite, String reservationName, Date arrivalDate, Date departureDate);
}
