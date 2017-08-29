package com.cognifide.interview.planerv2.server;

import com.cognifide.interview.planerv2.server.services.RestServices;

import io.advantageous.boon.core.Sys;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.server.EndpointServerBuilder;
import io.advantageous.qbit.server.ServiceEndpointServer;

@RequestMapping("/services")
public class SimpleRestServer {

	private static final int PORT_VALUE = 6060;
	private static ServiceEndpointServer server;
	private static boolean isServerRunning = false;

	private static final RestServices restServices = new RestServices();
	
	
	public static void main(String... args) {
		start();
		Sys.sleep(1_000_000_000);
	}

	public static void start() {
		if (!isServerRunning()) {
			server = new EndpointServerBuilder().setPort(PORT_VALUE).build().initServices(restServices).startServer();
			setServerRunning(true);
		}
	}

	public static void stop() {
		if (isServerRunning()) {
			server.stop();
			setServerRunning(false);
		}
	}

	public static boolean isServerRunning() {
		return isServerRunning;
	}

	private static void setServerRunning(boolean isServerRunning) {
		SimpleRestServer.isServerRunning = isServerRunning;
	}

}