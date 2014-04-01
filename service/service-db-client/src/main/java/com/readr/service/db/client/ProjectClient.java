package com.readr.service.db.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.readr.model.project.DBProject;

public class ProjectClient {

	private Client client;
	private String url;
	
	ProjectClient(Client client, String url) {
		this.client = client;
		this.url = url;
	}

	public void create(String ns, DBProject project) {
		WebTarget target = client.target(url).path(ns);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(project));
	}
	
	public void update(String ns, String proj, DBProject project) {
		WebTarget target = client.target(url).path(ns).path(proj);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.json(project));
	}
	
	public void delete(String ns, String proj) {
		WebTarget target = client.target(url).path(ns).path(proj);
		Response response =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .delete();
	}

	public List<DBProject> listPublic() {
		WebTarget target = client.target(url).path("public");
		@SuppressWarnings("unchecked")
		List<DBProject> projects =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get(ArrayList.class);
		return projects;
	}
	
	public List<DBProject> list(String ns) {
		WebTarget target = client.target(url).path(ns);
		@SuppressWarnings("unchecked")
		List<DBProject> projects =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get(ArrayList.class);
		return projects;
	}
	
	public DBProject get(String ns, String proj) {
		WebTarget target = client.target(url).path(ns);
		DBProject project =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .get(DBProject.class);
		return project;		
	}
}
