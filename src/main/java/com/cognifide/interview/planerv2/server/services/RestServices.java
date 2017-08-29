package com.cognifide.interview.planerv2.server.services;


import org.apache.log4j.Logger;

import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.annotation.RequestMethod;

@RequestMapping("/planer")
public class RestServices {

	private static final Logger log = Logger.getLogger(RestServices.class);

	BuildingServices buildingServices = new BuildingServices();

	/**
	 * curl -X POST http://localhost:6060/services/planer/building/add/hotel/2
	 * 
	 * @param name
	 * @param numberOfRooms
	 */
	@RequestMapping(value = "/building/add/{name}/{number}", method = RequestMethod.POST)
	public String createBuilding(
			@PathVariable("name") final String name,
			@PathVariable("number") final int numberOfRooms) {
			
		return buildingServices.createBuilding(name, numberOfRooms);
	}

	/**
	 * curl -X DELETE http://localhost:6060/services/planer/building/remove/hotel
	 * 
	 * @param name
	 */
	@RequestMapping(value = "/building/remove/{name}", method = RequestMethod.DELETE)
	public String removeBuilding(
			@PathVariable("name") final String name) {
			
		return buildingServices.removeBuilding(name);
	}

	/**
	 * curl -X GET http://localhost:6060/services/planer/building/get/hotel
	 * 
	 * @param name
	 */
	@RequestMapping(value = "/building/get/{name}", method = RequestMethod.GET)
	public String getBuilding(
			@PathVariable("name") final String name) {
		return buildingServices.getBuilding(name);
	}

	/**
	 * curl -X GET http://localhost:6060/services/planer/buildings
	 * 
	 * @param name
	 */
	@RequestMapping(value = "/buildings", method = RequestMethod.GET)
	public String getBuildings() {
		return buildingServices.getBuildings();
	}
	
	/**
	 * curl -X GET http://localhost:6060/services/planer/building/details/hotel/1
	 * 
	 * @param buildingName
	 * @return
	 */
	@RequestMapping(value = "/building/details/{buildingName}/{roomNumber}", method = RequestMethod.GET)//TODO nie dziala bo metoda powyzej przykrywa
	public String getRoom(
			@PathVariable("buildingName") final String buildingName,
			@PathVariable("roomNumber") final int roomNumber) {

		return buildingServices.getRoom(buildingName, roomNumber);
	}


	/**
	 * curl -X POST http://localhost:6060/services/planer/reserve/hotel/2/2018-08-27T12:00/2018-08-27T13:00
	 * curl -X POST http://localhost:6060/services/planer/reserve/hotel/2/2018-08-27T12:00/2018-08-27T13:00
	 * 
	 * 
	 * @param name
	 * @param numberOfRooms
	 */
	@RequestMapping(value = "/reserve/{buildingName}/{roomNumber}/{startTime}/{endTime}", method = RequestMethod.POST)
	public String createReservation (
			@PathVariable("buildingName") final String buildingName,
			@PathVariable("roomNumber") final int roomNumber,
			@PathVariable("startTime") final String startTime,
			@PathVariable("endTime") final String endTime) {
			
		return buildingServices.createReservation(buildingName, roomNumber, startTime, endTime);
	}

	
	/**
	 * curl -X GET http://localhost:6060/services/planer/available/building/room/hotel/2
	 * 
	 * 
	 * @param name
	 * @param numberOfRooms
	 */
	@RequestMapping(value = "/available/building/room/{buildingName}/{roomNumber}", method = RequestMethod.GET)	
	public String getAvailableReservations(
			@PathVariable("buildingName") final String buildingName,
			@PathVariable("roomNumber") final int roomNumber
			)	{
		return buildingServices.getAvailableReservations(buildingName, roomNumber);
	}

	/**
	 * curl -X GET http://localhost:6060/services/planer/available/building/hotel
	 * 
	 * 
	 * @param name
	 * @param numberOfRooms
	 */
	@RequestMapping(value = "/available/building/{buildingName}", method = RequestMethod.GET)	
	public String getAllAvailableReservations(
			@PathVariable("buildingName") final String buildingName
			)	{
		return buildingServices.getAllAvailableReservations(buildingName);
	}

	


}
