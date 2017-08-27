package com.cognifide.interview.planerv2.model.buildingstructure.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.reservation.request.AvailableReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class RoomMeetingTimeFinder {

	public ArrayList<AvailableReservationRequest> getAvailableReservations(ArrayList<ReservationRequest> reservations)
			throws DataTimeValidatorException {
		
		ArrayList<AvailableReservationRequest> result = new ArrayList<>();
		List<ReservationRequest> sortedReservations = new ArrayList<>(reservations);
		Collections.sort(sortedReservations);

		LocalDateTime beginOfUniverse = LocalDateTime.now();
		LocalDateTime endOfUniverse = LocalDateTime.now().plusYears(1000);
		
		if (sortedReservations.size() <= 0) {
			roomAlwaysAvailable(result, beginOfUniverse, endOfUniverse);
		} else {
			createAvailableIntervals(result, sortedReservations, beginOfUniverse, endOfUniverse);
		}
		return result;
	}

	private void roomAlwaysAvailable(ArrayList<AvailableReservationRequest> result, LocalDateTime beginOfUniverse,
			LocalDateTime endOfUniverse) throws DataTimeValidatorException {
		
		AvailableReservationRequest reservation = new AvailableReservationRequest(beginOfUniverse, endOfUniverse);
		result.add(reservation);
	}

	private void createAvailableIntervals(ArrayList<AvailableReservationRequest> result,
			List<ReservationRequest> sortedReservations, LocalDateTime beginOfUniverse, LocalDateTime endOfUniverse)
			throws DataTimeValidatorException {
		
		addFirstInterval(result, sortedReservations, beginOfUniverse);
		addMidIntervals(result, sortedReservations);
		addLastInterval(result, sortedReservations, endOfUniverse);
	}

	private void addFirstInterval(ArrayList<AvailableReservationRequest> result,
			List<ReservationRequest> sortedReservations, LocalDateTime beginOfUniverse)
			throws DataTimeValidatorException {
		
		AvailableReservationRequest firtReservation = new AvailableReservationRequest(beginOfUniverse,
				sortedReservations.get(0).getStartTime());
		result.add(firtReservation);
	}

	private void addMidIntervals(ArrayList<AvailableReservationRequest> result,
			List<ReservationRequest> sortedReservations) throws DataTimeValidatorException {
		
		for (int i = 1; i < sortedReservations.size(); i++) {
			AvailableReservationRequest nextReservation = new AvailableReservationRequest(
					sortedReservations.get(i - 1).getEndTime(), sortedReservations.get(i).getStartTime());
			result.add(nextReservation);
		}
	}

	private void addLastInterval(ArrayList<AvailableReservationRequest> result,
			List<ReservationRequest> sortedReservations, LocalDateTime endOfUniverse)
			throws DataTimeValidatorException {
		
		AvailableReservationRequest lastReservation = new AvailableReservationRequest(
				sortedReservations.get(sortedReservations.size() - 1).getEndTime(), endOfUniverse);
		result.add(lastReservation);
	}

}
