package com.cognifide.interview.planerv2.model.buildingstructure;

import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cognifide.interview.planerv2.model.reservation.exception.ReservationException;
import com.cognifide.interview.planerv2.model.reservation.exception.ReservationExceptionMsg;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class RoomValidatorTest {

	static RoomValidator roomValidator;

	ReservationRequest reservation;
	LocalDateTime startTime;
	LocalDateTime endTime;
	Room firstRoom;

	@BeforeClass
	public static void initializeValidator() {
		roomValidator = new RoomValidator();
	}

	@Before
	public void initializeSampleValues() throws DataTimeValidatorException {
		startTime = LocalDateTime.now().plusDays(1).withHourOfDay(12);
		endTime = startTime.plusMinutes(60);
		reservation = new ReservationRequest(startTime, endTime);
		firstRoom = new Room(1);

	}

	@Test
	public void shouldPass() {
		try {
			roomValidator.validate(firstRoom, reservation);
			Assert.assertEquals(1, firstRoom.getReservations().size());
		} catch (ReservationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(expected = ReservationException.class)
	public void sameValuesAdded() throws Exception {
		try {
			roomValidator.validate(firstRoom, reservation);
			roomValidator.validate(firstRoom, reservation);
			Assert.fail();
		} catch (ReservationException e) {
			Assert.assertEquals(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR, e.getMessage());
			throw e;
		}
	}

	@Test(expected = ReservationException.class)
	public void lowerBoundaryConditionFail() throws Exception {
		ReservationRequest newReservation = new ReservationRequest(startTime.minusMinutes(30), endTime.minusMinutes(30));

		try {
			roomValidator.validate(firstRoom, reservation);
			roomValidator.validate(firstRoom, newReservation);
			Assert.fail();
		} catch (ReservationException e) {
			Assert.assertEquals(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR, e.getMessage());
			throw e;
		}
	}

	@Test(expected = ReservationException.class)
	public void middleConditionFail() throws Exception {
		ReservationRequest newReservation = new ReservationRequest(startTime.plusMinutes(15), endTime.minusMinutes(15));

		try {
			roomValidator.validate(firstRoom, reservation);
			roomValidator.validate(firstRoom, newReservation);
			Assert.fail();
		} catch (ReservationException e) {
			Assert.assertEquals(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR, e.getMessage());
			throw e;
		}
	}

	@Test(expected = ReservationException.class)
	public void upperBoundaryConditionFail() throws Exception {
		ReservationRequest newReservation = new ReservationRequest(startTime.plusMinutes(30), endTime.plusMinutes(30));

		try {
			roomValidator.validate(firstRoom, reservation);
			roomValidator.validate(firstRoom, newReservation);
			Assert.fail();
		} catch (ReservationException e) {
			Assert.assertEquals(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR, e.getMessage());
			throw e;
		}
	}

	@Test(expected = ReservationException.class)
	public void wholeConditionFail() throws Exception {
		ReservationRequest newReservation = new ReservationRequest(startTime.minusMinutes(30), endTime.plusMinutes(30));

		try {
			roomValidator.validate(firstRoom, reservation);
			roomValidator.validate(firstRoom, newReservation);
			Assert.fail();
		} catch (ReservationException e) {
			Assert.assertEquals(ReservationExceptionMsg.ROOM_IS_ALREADY_BOOKED_ERROR, e.getMessage());
			throw e;
		}
	}

	@Test
	public void addIntervalBefore() {
		try {
			ReservationRequest newReservation = new ReservationRequest(startTime.minusMinutes(30), startTime.minusMinutes(15));
			roomValidator.validate(firstRoom, reservation);
			roomValidator.validate(firstRoom, newReservation);
			Assert.assertEquals(2, firstRoom.getReservations().size());
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		} catch (ReservationException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void addIntervalAfter() {
		try {
			ReservationRequest newReservation = new ReservationRequest(endTime.plusMinutes(15), endTime.plusMinutes(30));
			roomValidator.validate(firstRoom, reservation);
			roomValidator.validate(firstRoom, newReservation);
			Assert.assertEquals(2, firstRoom.getReservations().size());
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		} catch (ReservationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addIntervals() {
		try {
			roomValidator.validate(firstRoom, reservation);
			ReservationRequest newReservation = new ReservationRequest(endTime.plusMinutes(15), endTime.plusMinutes(45));
			roomValidator.validate(firstRoom, newReservation);
			Assert.assertEquals(2, firstRoom.getReservations().size());
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		} catch (ReservationException e) {
			e.printStackTrace();
		}
	}

}
