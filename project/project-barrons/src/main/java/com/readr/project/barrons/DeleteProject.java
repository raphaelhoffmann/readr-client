package com.readr.project.barrons;

import com.readr.model.project.DBProject;
import com.readr.service.db.client.ProjectClient;

public class DeleteProject {

	public static void main(String[] args) throws Exception {
		
		ProjectClient.delete("allenai", "barrons");
		
	}
	
}
