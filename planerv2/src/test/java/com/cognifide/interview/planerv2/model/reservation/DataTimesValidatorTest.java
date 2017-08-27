package com.cognifide.interview.planerv2.model.reservation;

import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cognifide.interview.planerv2.model.reservation.validator.ReservationDataTimeValidator;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorExceptionMsg;
import com.cognifide.interview.planerv2.model.reservation.validator.exception.DataTimeValidatorException;

public class DataTimesValidatorTest {

	static ReservationDataTimeValidator dataTimesValidator;

	LocalDateTime startTime;

	@BeforeClass
	public static void initializeValidator() {
		dataTimesValidator = new ReservationDataTimeValidator();
	}

	@Before
	public void initializeDataTime() {
		startTime = LocalDateTime.now().plusDays(1).withHourOfDay(12);
	}

	@Test
	public void shouldPassWithoutExceptionLowerBoundaryCondition() {
		LocalDateTime endTime = startTime.plusMinutes(15);
		try {
			dataTimesValidator.validate(startTime, endTime);
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldPassWithoutExceptionUpperBoundaryCondition() {
		LocalDateTime endTime = startTime.plusMinutes(120);
		try {
			dataTimesValidator.validate(startTime, endTime);
		} catch (DataTimeValidatorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldThowGreaterThan120Minutes() {
		LocalDateTime endTime = startTime.plusMinutes(150);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.RESERVATION_TIME_IS_GREATER_THAN_120_MINUTES_ERROR,
					e.getMessage());
		}
	}

	@Test
	public void shouldThowLessThan15Minutes() {
		LocalDateTime endTime = startTime.plusMinutes(10);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.RESERVATION_TIME_IS_LESS_THAN_15_MINUTES, e.getMessage());
		}
	}

	@Test
	public void shouldThowWrongDay() {
		LocalDateTime endTime = startTime.plusMinutes(60 * 24);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.DIFFERENT_DAY_ERROR, e.getMessage());
		}
	}

	@Test
	public void shouldThowWrongMonth() {
		LocalDateTime endTime = startTime.plusMinutes(60 * 24 * 40);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.DIFFERENT_MONTH_ERROR, e.getMessage());
		}
	}

	@Test
	public void shouldThowWrongYear() {
		LocalDateTime endTime = startTime.plusMinutes(60 * 24 * 40 * 10);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.DIFFERENT_YEAR_ERROR, e.getMessage());
		}
	}

	@Test
	public void shouldThowPastDate() {
		int initialFutureHour = 1;
		LocalDateTime startTime = LocalDateTime.now().minusHours(initialFutureHour);
		LocalDateTime endTime = startTime.plusMinutes(60 * 24 * 40 * 10);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.PAST_TIME_ERROR, e.getMessage());
		}
	}

	@Test
	public void shouldThowPastDate2() {
		LocalDateTime endTime = startTime.minusMinutes(60 * 24 * 40 * 10);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.PAST_TIME_ERROR, e.getMessage());
		}
	}

	@Test
	public void shouldThowStartTimeIsLessThanEndTime() {
		LocalDateTime endTime = startTime;
		LocalDateTime startTime = endTime.plusMinutes(10);
		try {
			dataTimesValidator.validate(startTime, endTime);
			Assert.fail();
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.RESERVATION_START_TIME_IS_LESS_THAN_END_TIME_ERROR,
					e.getMessage());
		}
	}

	@Test
	public void shouldPassNow() {
		startTime = LocalDateTime.now();
		LocalDateTime endTime = startTime.plusMinutes(60);
		try {
			dataTimesValidator.validate(startTime, endTime);
		} catch (DataTimeValidatorException e) {
			Assert.assertEquals(DataTimeValidatorExceptionMsg.PAST_TIME_ERROR, e.getMessage());
		}
	}
	
}
