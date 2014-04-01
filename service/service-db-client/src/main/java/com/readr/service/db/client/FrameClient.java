package com.readr.service.db.client;

import javax.ws.rs.client.Client;

public class FrameClient {

	// TODO: authentication
	
	// https://github.com/jersey/jersey/blob/master/examples/oauth2-client-google-webapp/src/main/java/org/glassfish/jersey/examples/oauth2/googleclient/SimpleOAuthService.java
	
	private Client client;
	private String url;
	
	FrameClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}
	
}
