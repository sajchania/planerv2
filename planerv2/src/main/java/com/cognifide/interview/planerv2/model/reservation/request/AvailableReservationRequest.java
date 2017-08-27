package com.cognifide.interview.planerv2.model.reservation.request;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.reservation.validator.AvailableReservationDataTimeValidator;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class AvailableReservationRequest extends AbstractReservationRequest{
	public AvailableReservationRequest(LocalDateTime startTime, LocalDateTime endTime) throws DataTimeValidatorException {
		super(new AvailableReservationDataTimeValidator(), startTime, endTime);
	}

	@Override
	public String toString() {
		return "AvailableReservation [startTime=" + getStartTime() + ", endTime=" + getEndTime() + "]";
	}
}
