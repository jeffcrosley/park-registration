package com.techelevator.parks.model;

import java.time.LocalDate;

public interface ReservationDAO {
	public Reservation createReservation(Site selectedSite, String reservationName, LocalDate arrivalDate, LocalDate departureDate);
}
