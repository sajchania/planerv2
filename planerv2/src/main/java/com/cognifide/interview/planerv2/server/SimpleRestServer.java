package com.cognifide.interview.planerv2.server;

import com.cognifide.interview.planerv2.server.services.RestServices;

import io.advantageous.boon.core.Sys;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.server.EndpointServerBuilder;
import io.advantageous.qbit.server.ServiceEndpointServer;

@RequestMapping("/services")
public class SimpleRestServer {

	public static void main(String... args) throws Exception {

		final ServiceEndpointServer server = new EndpointServerBuilder()
				.setPort(6060)
				.build()
				.initServices(new RestServices())
				.startServer();

		Sys.sleep(1_000_000_000);
	}

}