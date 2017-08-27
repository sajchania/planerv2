package com.cognifide.interview.planerv2.model.buildingstructure;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.cognifide.interview.planerv2.model.reservation.RoomReservation;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class BuildingTest {

	@Test
	public void simpleTest() {

		for (Room room : hotel.getRooms()) {
			try {
//				System.out.println(room.getAvailableReservations());
				ReservationRequest reservation = new ReservationRequest(
						LocalDateTime.now().withHourOfDay(12).plusDays(1),
						LocalDateTime.now().withHourOfDay(12).plusDays(1).plusMinutes(30));
					new RoomReservation(room, reservation);
				
//				System.out.println(room.getAvailableReservations());
			} catch (DataTimeValidatorException e) {
				e.printStackTrace();
			}
			
		}
	}

	Building hotel = new Building(2);

	Runnable reserve = new Runnable() {

		@Override
		public void run() {
			for (Room room : hotel.getRooms()) {
				try {
					ReservationRequest reservation = new ReservationRequest(
							LocalDateTime.now().withHourOfDay(12).plusDays(1),
							LocalDateTime.now().withHourOfDay(12).plusDays(1).plusMinutes(30));
					RoomReservation roomReservation = new RoomReservation(room, reservation);
					System.out.println(roomReservation.toString());
				} catch (DataTimeValidatorException e) {
					e.printStackTrace();
				}
			}
		};
	};

	@Test
	public void simpleTest2() {
		Thread reservationApp1 = new Thread(reserve);
		Thread reservationApp2 = new Thread(reserve);
		Thread reservationApp3 = new Thread(reserve);
		reservationApp1.start();
		reservationApp2.start();
		reservationApp3.start();
		try {
			reservationApp2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			reservationApp1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			reservationApp3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
