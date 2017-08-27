package com.cognifide.interview.planerv2.model.reservation;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


public class RoomReservationIdGeneratorTest {

	@Ignore
	@Test
	public void sampleTest() {
		RoomRevervationIdGenerator generator = new RoomRevervationIdGenerator();
		System.out.println(generator.getId());
		System.out.println(generator.getId());
		System.out.println(generator.getId());
		System.out.println(generator.getId());
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("firstThread " + generator.getId());
				}
			}
		});
		t1.setName("firstThread");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("secondThread " + generator.getId());
				}
			}
		});
		t2.setName("secondThread");
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(2005, RoomRevervationIdGenerator.getId());
	}
}
