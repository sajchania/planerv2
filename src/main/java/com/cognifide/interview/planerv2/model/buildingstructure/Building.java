package com.cognifide.interview.planerv2.model.buildingstructure;

import java.util.ArrayList;

public class Building {
	private ArrayList<Room> rooms;
	private String name;

	public Building(ArrayList<Room> rooms) {
		setRooms(rooms);
	}

	public Building(int numberOfRooms) {
		setRooms(numberOfRooms);
	}

	public Building(String name, int numberOfRooms) {
		this(numberOfRooms);
		setName(name);
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	private void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setRooms(int numberOfRooms) {
		rooms = new ArrayList<>();
		for (int roomNumber = 1; roomNumber <= numberOfRooms; roomNumber++) {
			Room newRoom = new Room(roomNumber);
			rooms.add(newRoom);
		}
	}

	@Override
	public String toString() {
		return "Building [name=" + name + ", numberOfRooms=" + rooms.size() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Building other = (Building) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
