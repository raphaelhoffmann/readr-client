package com.readr.service.hadoop.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.readr.model.module.job.Processor;

public class ProcessorClient {

	private Client client;
	private String url;
	
	ProcessorClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}

	public List<Processor> list() {
		String turl = url + "/processes/list";
		WebTarget target = client.target(turl);
		@SuppressWarnings("unchecked")
		List<Processor> ps =
				target.request(MediaType.APPLICATION_JSON_TYPE)
				    .get(ArrayList.class);
		return ps;
	}
}
