package com.cognifide.interview.planerv2.model.reservation;

import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cognifide.interview.planerv2.model.buildingstructure.Room;
import com.cognifide.interview.planerv2.model.reservation.exception.ReservationException;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class RoomReservationTest {

	ReservationRequest reservation;
	LocalDateTime startTime;
	LocalDateTime endTime;
	Room firstRoom;
	Room secondRoom;

	@Before
	public void initialize() {
		startTime = LocalDateTime.now().plusDays(1).withHourOfDay(12);
		endTime = startTime.plusMinutes(60);
		firstRoom = new Room(1);
		secondRoom = new Room(2);
	}

	@Test
	public void firstAvailableReservation() {
		try {
			firstRoom.getAvailableReservations();
			Assert.assertEquals(1, firstRoom.getAvailableReservations().size());
			Assert.assertEquals(LocalDateTime.now().plusYears(1000).getYear(),
					firstRoom.getAvailableReservations().get(0).getEndTime().getYear());
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		}
	}

	@Test
	@SuppressWarnings("unused")
	public void AvailableReservationTwoIntervals() {
		try {
			RoomReservation roomReservation = new RoomReservation(firstRoom, startTime, endTime);
			Assert.assertEquals(2, firstRoom.getAvailableReservations().size());
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		} catch (ReservationException e) {
			e.printStackTrace();
		}
	}

	@Test
	@SuppressWarnings("unused")
	public void AvailableReservationCoupleIntervals() {
		try {
			RoomReservation roomReservation = new RoomReservation(firstRoom, startTime, endTime);
			RoomReservation roomReservation2 = new RoomReservation(firstRoom, startTime.plusDays(1),
					endTime.plusDays(1));
			RoomReservation roomReservation3 = new RoomReservation(firstRoom, startTime.plusDays(2),
					endTime.plusDays(2));
			Assert.assertEquals(4, firstRoom.getAvailableReservations().size());
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		} catch (ReservationException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	@SuppressWarnings("unused")
	public void AvailableReservationCoupleIntervalsTwoRooms() {
		try {
			RoomReservation roomReservation = new RoomReservation(firstRoom, startTime, endTime);
			RoomReservation roomReservation2 = new RoomReservation(secondRoom, startTime.plusDays(1),
					endTime.plusDays(1));
			RoomReservation roomReservation3 = new RoomReservation(firstRoom, startTime.plusDays(2),
					endTime.plusDays(2));
			RoomReservation roomReservation4 = new RoomReservation(secondRoom, startTime.plusDays(2),
					endTime.plusDays(2));

			Assert.assertEquals(3, firstRoom.getAvailableReservations().size());
			Assert.assertEquals(3, secondRoom.getAvailableReservations().size());
			Assert.assertEquals(firstRoom.getAvailableReservations().get(2).getStartTime(), secondRoom.getAvailableReservations().get(2).getStartTime());
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		} catch (ReservationException e) {
			e.printStackTrace();
		}
	}
	
}
