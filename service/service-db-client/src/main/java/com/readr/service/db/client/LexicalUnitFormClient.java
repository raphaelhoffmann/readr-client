package com.readr.service.db.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.readr.model.module.lexical.LexicalUnitForm;

public class LexicalUnitFormClient {

	private Client client;
	private String url;
	
	LexicalUnitFormClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}

	public void add(String ns, String proj, LexicalUnitForm f) {
		String turl = url + "/" + ns + "/" + proj + "/meaning/lexicalForm/" + f.lexicalID;
		WebTarget target = client.target(turl);
		f.lexicalFormID =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(f), Integer.class);		
	}

	public void update(String ns, String proj, LexicalUnitForm f) {
		String turl = url + "/" + ns + "/" + proj + "/meaning/lexicalForm/" + f.lexicalID + "/" + f.lexicalFormID;
		WebTarget target = client.target(turl);
		target.request(MediaType.APPLICATION_JSON_TYPE)
		    .post(Entity.json(f));
	}

	public void delete(String ns, String proj, int lexicalID, int lexicalFormID) {
		String turl = url + "/" + ns + "/" + proj + "/meaning/lexicalForm/" + lexicalID + "/" + lexicalFormID;
		WebTarget target = client.target(turl);
		target.request().delete();
	}
}