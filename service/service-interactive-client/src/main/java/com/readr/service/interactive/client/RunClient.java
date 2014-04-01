package com.readr.service.interactive.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RunClient {

	private Client client;
	private String url;
	
	RunClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}

	public void setContentAndProcess(String ns, String proj, int id, String text) throws Exception {
		ProcPlan plan = new ProcPlan();
		List<ProcPlanStep> steps = new ArrayList<ProcPlanStep>();
		steps.add(new ProcPlanStep("test1"));
		steps.add(new ProcPlanStep("test2"));
		plan.setSteps(steps);

		ProcPlanRunRequest r = new ProcPlanRunRequest();
		r.setPlan(plan);
		r.setText(text);
		r.setId(id);
		
		String turl = url + "/" + ns + "/" + proj + "/plan";
		
		WebTarget target = client.target(turl);
		 
		String response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(r), String.class);
	}
}
