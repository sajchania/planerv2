package com.cognifide.interview.planerv2.model.reservation.validator;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class AvailableReservationDataTimeValidator extends AbstractDataTimeValidator {

	@Override
	public void validate(LocalDateTime startTime, LocalDateTime endTime) throws DataTimeValidatorException {
		isPastValue(startTime.plusMinutes(1));
		isPastValue(endTime.plusMinutes(1));
		isStartTimeGreaterThanEndTime(startTime, endTime);
	}

}
