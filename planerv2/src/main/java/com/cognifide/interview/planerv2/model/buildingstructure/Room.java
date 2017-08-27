package com.cognifide.interview.planerv2.model.buildingstructure;

import java.util.ArrayList;

import com.cognifide.interview.planerv2.model.buildingstructure.utils.RoomMeetingTimeFinder;
import com.cognifide.interview.planerv2.model.reservation.request.AvailableReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class Room {

	private int number;
	private ArrayList<ReservationRequest> reservations;
	private RoomMeetingTimeFinder roomMeetingTimeFinder = new RoomMeetingTimeFinder();

	public Room(int number) {
		setNumber(number);
		setReservations(new ArrayList<>());
	}

	public int getNumber() {
		return number;
	}

	private void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<ReservationRequest> getReservations() {
		return reservations;
	}

	private void setReservations(ArrayList<ReservationRequest> reservations) {
		this.reservations = reservations;
	}

	public void addReservation(ReservationRequest reservation) {
		reservations.add(reservation);
	}

	public ArrayList<AvailableReservationRequest> getAvailableReservations() throws DataTimeValidatorException {
		return roomMeetingTimeFinder.getAvailableReservations(reservations);
	}

	@Override
	public String toString() {
		return "Room [number=" + number + ", reservations=" + reservations + "]";
	}

}
