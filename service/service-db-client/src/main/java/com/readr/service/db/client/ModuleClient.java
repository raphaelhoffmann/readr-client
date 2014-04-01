package com.readr.service.db.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ModuleClient {
	
	private Client client;
	private String url;
	
	ModuleClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}

	public void createOnDatabase(String ns, String proj, String name) {
		WebTarget target = client.target(url).path(ns).path(proj).path("module").path("createOnDatabase").path(name);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get();
	}

	public void dropFromDatabase(String ns, String proj, String name) {
		WebTarget target = client.target(url).path(ns).path(proj).path("module").path("dropFromDatabase").path(name);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get();		
	}
	
}
