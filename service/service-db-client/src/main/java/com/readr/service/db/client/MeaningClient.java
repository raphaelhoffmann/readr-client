package com.readr.service.db.client;

import javax.ws.rs.client.Client;

public class MeaningClient {

	public final FrameClient frame;
	public final LexicalUnitClient lexical;
	public final LexicalUnitFormClient lexicalForm;
	
	MeaningClient(Client client, String url) {
		this.frame = new FrameClient(client, url);
		this.lexical = new LexicalUnitClient(client, url);
		this.lexicalForm = new LexicalUnitFormClient(client, url);
	}
}
