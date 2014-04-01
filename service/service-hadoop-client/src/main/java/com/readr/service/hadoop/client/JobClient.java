package com.readr.service.hadoop.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.readr.model.module.job.JobRequest;
import com.readr.model.module.job.JobStatus;

public class JobClient {

	private Client client;
	private String url;
	
	JobClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}

	public void submit(String ns, String proj, JobRequest jr) {
		String turl = url + "/" + ns + "/" + proj + "/job/submit";
		WebTarget target = client.target(turl);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(jr));
	}
	
	public void cancel(String ns, String proj, int runtimeJobID) {
		String turl = url + "/" + ns + "/" + proj + "/job/cancel/" + runtimeJobID;
		WebTarget target = client.target(turl);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get();
	}
	
	public JobStatus status(String ns, String proj, int runtimeJobID) {
		String turl = url + "/" + ns + "/" + proj + "/job/status/" + runtimeJobID;
		WebTarget target = client.target(turl);
		JobStatus js =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get(JobStatus.class);
		return js;
	}

	public List<JobStatus> listStatuses(String ns, String proj, List<Integer> jobIDs) {
		String turl = url + "/" + ns + "/" + proj + "/job/statuses";
		WebTarget target = client.target(turl);
		@SuppressWarnings("unchecked")
		List<JobStatus> jss =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(jobIDs), ArrayList.class);
		return jss;
	}
}
