package com.readr.service.interactive.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

public class ReadrInteractiveClient {

	public static String DEFAULT_HOST = "preview.readr.com";
	public static int    DEFAULT_PORT = 9070;
	
	private String host;
	private int port;
	
	private Client client;

	public final RunClient run;
	
	public ReadrInteractiveClient() {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}
	
	public ReadrInteractiveClient(String host, int port) {
		this.host = host;
		this.port = port;

		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
        cc.property(ClientProperties.CONNECT_TIMEOUT,  100000);
        cc.property(ClientProperties.READ_TIMEOUT,    100000);
        client = ClientBuilder.newClient(cc);
        String url = "http://" + host + ":" + port;
        
        run = new RunClient(client, url);
	}

	public ReadrInteractiveClient(String host, int port, String user, String password) {
		this(host, port);
		authenticate(user, password);
	}

	public void authenticate(String user, String password) {
        HttpAuthenticationFeature feature = 
        		HttpAuthenticationFeature.basic(user, password);        
        client.register(feature);
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
}