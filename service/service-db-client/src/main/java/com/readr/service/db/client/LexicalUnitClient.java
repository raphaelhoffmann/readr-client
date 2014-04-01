package com.readr.service.db.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.readr.model.module.lexical.LexicalUnit;

public class LexicalUnitClient {

	private Client client;
	private String url;
	
	LexicalUnitClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}
		
	public void add(String ns, String proj, LexicalUnit lu) {
		String turl = url + "/" + ns + "/" + proj + "/meaning/lexical";
		WebTarget target = client.target(turl);
		lu.lexicalID =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(lu), Integer.class);
	}

	public void update(String ns, String proj, LexicalUnit lu) {
		String turl = url + "/" + ns + "/" + proj + "/meaning/lexical/" + lu.lexicalID;
		WebTarget target = client.target(turl);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(lu));
	}

	public void delete(String ns, String proj, int lexicalID) {
		String turl = url + "/" + ns + "/" + proj + "/meaning/lexical/" + lexicalID;
		WebTarget target = client.target(turl);
		Response response =
			target.request().delete();
	}
}
