package com.cognifide.interview.planerv2.model.reservation;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.buildingstructure.Room;
import com.cognifide.interview.planerv2.model.buildingstructure.RoomValidator;
import com.cognifide.interview.planerv2.model.reservation.exception.ReservationException;
import com.cognifide.interview.planerv2.model.reservation.exception.ReservationExceptionMsg;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class RoomReservation {

	private int id;
	private Room room;
	private ReservationRequest reservation;
	private RoomValidator roomValidator = new RoomValidator();
	private boolean isSuccesful = false;
	private String errorMsg;

	public RoomReservation(Room room, LocalDateTime startTime, LocalDateTime endTime)
			throws DataTimeValidatorException, ReservationException {
		this(room, new ReservationRequest(startTime, endTime));
	}

	public RoomReservation(Room room, ReservationRequest reservation){
		if (room != null) {
			if (reservation != null) {
				setId();
				setRoom(room);
				setReservation(reservation);
				try {
					validateReservation();
				} catch (ReservationException e) {
					errorMsg = e.getMessage();
				}
			} else {
				errorMsg = ReservationExceptionMsg.RESERVATION_IS_EQUAL_TO_NULL_ERROR; 
			}
		} else {
			errorMsg = ReservationExceptionMsg.ROOM_IS_EQUAL_TO_NULL_ERROR;
		}
	}

	public Room getRoom() {
		return room;
	}

	private void setRoom(Room room) {
		this.room = room;
	}

	public ReservationRequest getReservation() {
		return reservation;
	}

	private void setReservation(ReservationRequest reservation) {
		this.reservation = reservation;
	}

	private void validateReservation() throws ReservationException {
		roomValidator.validate(room, reservation);
		setSuccesful(true);
	}

	public boolean isSuccesful() {
		return isSuccesful;
	}

	private void setSuccesful(boolean isSuccesful) {
		this.isSuccesful = isSuccesful;
	}

	@Override
	public String toString() {
		return "RoomReservation [id=" + id + ", reservation=" + reservation + ", room=" + room + ", isSuccesful="
				+ isSuccesful + ", errorMsg=" + errorMsg +"]";
	}

	public String toStringWithoutDetails() {
		return "RoomReservation [id=" + id + ", reservation=" + reservation + ", room=" + room.getNumber() + ", isSuccesful="
				+ isSuccesful + ", errorMsg=" + errorMsg +"]";
	}
	
	public int getId() {
		return id;
	}

	private void setId() {
		this.id = RoomRevervationIdGenerator.getId();
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}


}
