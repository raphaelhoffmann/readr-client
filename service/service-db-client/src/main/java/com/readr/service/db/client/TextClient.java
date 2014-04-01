package com.readr.service.db.client;

import javax.ws.rs.client.Client;

public class TextClient {

	public final DocumentClient document;
	
	TextClient(Client client, String url) {
		this.document = new DocumentClient(client, url);
	}
}
