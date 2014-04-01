package com.readr.service.db.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.readr.model.module.text.Text;

public class DocumentClient {

	private Client client;
	private String url;
	
	DocumentClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}
	
	public void add(String ns, String proj, Text t) throws Exception {
		String turl = url + "/" + ns + "/" + proj + "/text";
		WebTarget target = client.target(turl);		 
		t.documentID =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(t), Integer.class);
	}

	public void update(String ns, String proj, Text t) throws Exception {
		String turl = url + "/" + ns + "/" + proj + "/text/" + t.documentID;
		WebTarget target = client.target(turl);		 
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(t), Integer.class);
	}

	public Text get(String ns, String proj, int id) throws Exception {
		String turl = url + "/" + ns + "/" + proj + "/text/" + id;		
		WebTarget target = client.target(turl);		 
		Text t = target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get(Text.class);
		return t;
	}
	
//	public static void main(String[] args) throws Exception {
//		get("allenai", "barrons", 70);
//	}
}
