package com.cognifide.interview.planerv2.model.reservation.validator.exception;

public class DataTimeValidatorExceptionMsg {

	public static final String RESERVATION_TIME_IS_GREATER_THAN_120_MINUTES_ERROR = "Reservation time is greater than 120 minutes";
	public static final String RESERVATION_TIME_IS_LESS_THAN_15_MINUTES = "Reservation time is less than 15 minutes";
	public static final String RESERVATION_START_TIME_IS_LESS_THAN_END_TIME_ERROR = "Reservation start time is less than end time";
	public static final String DIFFERENT_DAY_ERROR = "Different day";
	public static final String DIFFERENT_MONTH_ERROR = "Different month";
	public static final String DIFFERENT_YEAR_ERROR = "Different year";
	public static final String PAST_TIME_ERROR = "Reservation is in the past";
}
