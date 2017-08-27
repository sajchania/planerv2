package com.cognifide.interview.planerv2.model.reservation.validator;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class ReservationDataTimeValidator extends AbstractDataTimeValidator {

	@Override
	public void validate(LocalDateTime startTime, LocalDateTime endTime) throws DataTimeValidatorException {
		isPastValue(startTime);
		isPastValue(endTime);
		isDifferentDay(startTime, endTime);
		isStartTimeGreaterThanEndTime(startTime, endTime);
		isReservationTimeGreaterThan15MinutesAndLessThan120Minutes(startTime, endTime);
	}

}
