package com.cognifide.interview.planerv2.model.reservation.request;

import org.joda.time.LocalDateTime;

import com.cognifide.interview.planerv2.model.reservation.validator.AbstractDataTimeValidator;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public abstract class AbstractReservationRequest implements Comparable<AbstractReservationRequest> {

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private AbstractDataTimeValidator dataTimesValidator;

	protected AbstractReservationRequest(AbstractDataTimeValidator dataTimesValidator, LocalDateTime startTime,
			LocalDateTime endTime) throws DataTimeValidatorException {
		setDataTimesValidator(dataTimesValidator);
		dataTimesValidator.validate(startTime, endTime);
		setStartTime(startTime);
		setEndTime(endTime);
	}

	public AbstractDataTimeValidator getDataTimesValidator() {
		return dataTimesValidator;
	}

	public void setDataTimesValidator(AbstractDataTimeValidator dataTimesValidator) {
		this.dataTimesValidator = dataTimesValidator;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	private void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	private void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "AbstractReservation [startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	public int compareTo(AbstractReservationRequest o) {
		if (this.startTime.getYear() < o.startTime.getYear()) {
			return -1;
		} else if (this.startTime.getYear() > o.startTime.getYear()) {
			return 1;
		} else {
			if (this.startTime.getMonthOfYear() < o.startTime.getMonthOfYear()) {
				return -1;
			} else if (this.startTime.getMonthOfYear() > o.startTime.getMonthOfYear()) {
				return 1;
			} else {
				if (this.startTime.getDayOfMonth() < o.startTime.getDayOfMonth()) {
					return -1;
				} else if (this.startTime.getDayOfMonth() > o.startTime.getDayOfMonth()) {
					return 1;
				} else {
					if (this.startTime.getMillisOfDay() < o.startTime.getMillisOfDay()) {
						return -1;
					} else if (this.startTime.getMillisOfDay() > o.startTime.getMillisOfDay()) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		}
	}

}
