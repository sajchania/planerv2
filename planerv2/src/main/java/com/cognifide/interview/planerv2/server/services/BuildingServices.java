package com.cognifide.interview.planerv2.server.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import com.cognifide.interview.planerv2.model.buildingstructure.Building;
import com.cognifide.interview.planerv2.model.buildingstructure.Room;
import com.cognifide.interview.planerv2.model.reservation.RoomReservation;
import com.cognifide.interview.planerv2.model.reservation.request.ReservationRequest;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class BuildingServices {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm";

	private static final Logger log = Logger.getLogger(BuildingServices.class);

	private HashMap<String, Building> listOFBuildings = new HashMap<>();

	public HashMap<String, Building> getListOFBuildings() {
		return listOFBuildings;
	}

	public String createBuilding(final String name, final int numberOfRooms) {
		Building building = new Building(name, numberOfRooms);
		if (listOFBuildings.containsKey(name)) {
			return "ERROR: Building already exists";
		} else {
			listOFBuildings.put(building.getName(), building);
			log.debug("Building created: " + building.toString());
			return "SUCCESS";
		}
	}

	public String removeBuilding(final String name) {
		if (!listOFBuildings.containsKey(name)) {
			return "ERROR: Building does not exists";
		} else {
			log.debug("Building removed: name=" + name);
			listOFBuildings.remove(name);
			return "SUCCESS";
		}
	}

	public String getBuilding(final String name) {
		if (!listOFBuildings.containsKey(name)) {
			return "ERROR: Building does not exists";
		} else {
			return listOFBuildings.get(name).toString();
		}
	}

	public String getBuildings() {
		if (listOFBuildings.isEmpty()) {
			return "ERROR: There are no buildings";
		} else {
			return listOFBuildings.toString();
		}
	}

	public String getRoom(final String buildingName, final int roomNumber) {
		if (!listOFBuildings.containsKey(buildingName)) {
			return "ERROR: Building does not exists";
		} else {
			Building building = listOFBuildings.get(buildingName);
			Room room = null;
			try {
				room = building.getRooms().get(roomNumber - 1);
			} catch (IndexOutOfBoundsException e) {
				return "ERROR: Room does not exists";
			}
			if (room == null) {
				return "ERROR: Room is equal to null";
			} else {
				return room.toString();
			}
		}
	}

	public String createReservation(final String buildingName, final int roomNumber, final String startTime,
			final String endTime) {
		if (!listOFBuildings.containsKey(buildingName)) {
			return "ERROR: Building does not exists";
		} else {
			Building building = listOFBuildings.get(buildingName);
			Room room = null;
			try {
				room = building.getRooms().get(roomNumber - 1);
			} catch (IndexOutOfBoundsException e) {
				return "ERROR: Room does not exists";
			}
			if (room == null) {
				return "ERROR: Room is equal to null";
			} else {
				LocalDateTime localStartTime = LocalDateTime.parse(startTime,
						DateTimeFormat.forPattern(DATE_TIME_FORMAT));
				if (localStartTime == null) {
					return "ERROR: Invalid start time. Expected DateFormat: " + DATE_TIME_FORMAT;
				}
				LocalDateTime localEndTime = LocalDateTime.parse(endTime, DateTimeFormat.forPattern(DATE_TIME_FORMAT));
				if (localEndTime == null) {
					return "ERROR: Invalid end time. Expected DateFormat: " + DATE_TIME_FORMAT;
				}
				ReservationRequest reservation = null;
				try {
					reservation = new ReservationRequest(localStartTime, localEndTime);
				} catch (DataTimeValidatorException e) {
					return "ERROR: " + e.getMessage();
				}
				RoomReservation roomReservation = new RoomReservation(room, reservation);
				if (!roomReservation.isSuccesful()) {
					return "ERROR: " + roomReservation.toStringWithoutDetails();
				} else {
					return "SUCCESS: " + roomReservation.toStringWithoutDetails();
				}
			}

		}
	}

	public String getAvailableReservations(String buildingName, int roomNumber) {
		if (!listOFBuildings.containsKey(buildingName)) {
			return "ERROR: Building does not exists";
		} else {
			Building building = listOFBuildings.get(buildingName);
			Room room = null;
			try {
				room = building.getRooms().get(roomNumber - 1);
			} catch (IndexOutOfBoundsException e) {
				return "ERROR: Room does not exists";
			}
			if (room == null) {
				return "ERROR: Room is equal to null";
			} else {
				try {
					return room.getAvailableReservations().toString();
				} catch (DataTimeValidatorException e) {
					return "ERROR: " + e.getMessage();
				}
			}
		}
	}

	public String getAllAvailableReservations(String buildingName) {
		if (!listOFBuildings.containsKey(buildingName)) {
			return "ERROR: Building does not exists";
		} else {
			Building building = listOFBuildings.get(buildingName);
			ArrayList<Room> rooms = building.getRooms();

			if (rooms == null || rooms.size() == 0) {
				return "ERROR: No rooms in the building";
			} else {
				return rooms.toString();
			}
		}
	}

}
