package com.cognifide.interview.planerv2.model.reservation;

import java.util.concurrent.atomic.AtomicInteger;

public class RoomRevervationIdGenerator {
	private static AtomicInteger uniqueId = new AtomicInteger(0);
	
	public static int getId() {
		return uniqueId.incrementAndGet();
	}
}
