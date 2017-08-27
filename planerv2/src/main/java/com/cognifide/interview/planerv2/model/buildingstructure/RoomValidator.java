package com.cognifide.interview.planerv2.model.buildingstructure;

import java.util.ArrayList;

import com.cognifide.interview.planerv2.model.reservation.exception.ReservationException;
import com.cognifide.interview.planerv2.model.reservation.exception.ReservationExceptionMsg;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;

public class RoomValidator {

	public void validate(Room room, ReservationRequest reservation) throws ReservationException {
		ArrayList<ReservationRequest> previousReservations = room.getReservations();
		for (ReservationRequest previousReservation : previousReservations) {
			if (previousReservation.getStartTime().compareTo(reservation.getStartTime()) >= 0
					&& previousReservation.getStartTime().compareTo(reservation.getEndTime()) <= 0) {
				throw new ReservationException(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR);
			}

			if (previousReservation.getStartTime().compareTo(reservation.getStartTime()) <= 0
					&& previousReservation.getEndTime().compareTo(reservation.getEndTime()) >= 0) {
				throw new ReservationException(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR);
			}

			if (previousReservation.getEndTime().compareTo(reservation.getStartTime()) >= 0
					&& previousReservation.getEndTime().compareTo(reservation.getEndTime()) <= 0) {
				throw new ReservationException(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR);
			}
		}
		room.addReservation(reservation);
	}

}
