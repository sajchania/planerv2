package com.cognifide.interview.planerv2.model.reservation.validator;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorExceptionMsg;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public abstract class AbstractDataTimeValidator {

	private static final int FIFTEEN_MINUTES_IN_MILLIS = 15 * 60 * 1000;
	private static final int ONE_HUNDRED_TWENTY_MINUTES_IN_MILLIS = 120 * 60 * 1000;
	
	public abstract void validate(LocalDateTime startTime, LocalDateTime endTime) throws DataTimeValidatorException; 

	protected void isPastValue(LocalDateTime startTime) throws DataTimeValidatorException {
		LocalDateTime now = new LocalDateTime();
		if (startTime.compareTo(now) <= 0) {
			throw new DataTimeValidatorException(DataTimeValidatorExceptionMsg.PAST_TIME_ERROR);
		}
	}

	protected void isDifferentDay(LocalDateTime startTime, LocalDateTime endTime) throws DataTimeValidatorException {
		if (startTime.getYear() != endTime.getYear()) {
			throw new DataTimeValidatorException(DataTimeValidatorExceptionMsg.DIFFERENT_YEAR_ERROR);
		} else if (startTime.getMonthOfYear() != endTime.getMonthOfYear()) {
			throw new DataTimeValidatorException(DataTimeValidatorExceptionMsg.DIFFERENT_MONTH_ERROR);
		} else if (startTime.getDayOfMonth() != endTime.getDayOfMonth()) {
			throw new DataTimeValidatorException(DataTimeValidatorExceptionMsg.DIFFERENT_DAY_ERROR);
		}

	}

	protected void isStartTimeGreaterThanEndTime(LocalDateTime startTime, LocalDateTime endTime)
			throws DataTimeValidatorException {
		if (startTime.compareTo(endTime) >= 0) {
			throw new DataTimeValidatorException(DataTimeValidatorExceptionMsg.RESERVATION_START_TIME_IS_LESS_THAN_END_TIME_ERROR);
		}

	}

	protected void isReservationTimeGreaterThan15MinutesAndLessThan120Minutes(LocalDateTime startTime,
			LocalDateTime endTime) throws DataTimeValidatorException {
		int diffInMillis = endTime.getMillisOfDay() - startTime.getMillisOfDay();
		if (diffInMillis < FIFTEEN_MINUTES_IN_MILLIS) {
			throw new DataTimeValidatorException(DataTimeValidatorExceptionMsg.RESERVATION_TIME_IS_LESS_THAN_15_MINUTES);
		} else if (diffInMillis > ONE_HUNDRED_TWENTY_MINUTES_IN_MILLIS) {
			throw new DataTimeValidatorException(DataTimeValidatorExceptionMsg.RESERVATION_TIME_IS_GREATER_THAN_120_MINUTES_ERROR);
		}

	}

	
	
}
