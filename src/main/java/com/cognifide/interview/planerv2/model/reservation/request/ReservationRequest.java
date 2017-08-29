package com.cognifide.interview.planerv2.model.reservation.request;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.reservation.validator.ReservationDataTimeValidator;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class ReservationRequest extends AbstractReservationRequest {

	public ReservationRequest(LocalDateTime startTime, LocalDateTime endTime) throws DataTimeValidatorException {
		super(new ReservationDataTimeValidator(), startTime, endTime);
	}

	@Override
	public String toString() {
		return "Reservation [startTime=" + getStartTime() + ", endTime=" + getEndTime() + "]";
	}

	
}
