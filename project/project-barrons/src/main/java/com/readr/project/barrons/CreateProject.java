package com.readr.project.barrons;

import com.readr.model.project.DBProject;
import com.readr.service.db.client.ModuleClient;
import com.readr.service.db.client.ProjectClient;


public class CreateProject {

	public static void main(String[] args) throws Exception {
		
		DBProject p = new DBProject();
		p.ns = "allenai";
		p.proj = "barrons";
		p.scope = "public";
		p.description = "";
		
		ProjectClient.create("allenai", p);
		
		
		ModuleClient.createOnDatabase("allenai", "barrons", "text");
		ModuleClient.createOnDatabase("allenai", "barrons", "token");
		ModuleClient.createOnDatabase("allenai", "barrons", "sentence");
		ModuleClient.createOnDatabase("allenai", "barrons", "layer");
		ModuleClient.createOnDatabase("allenai", "barrons", "frame");
		ModuleClient.createOnDatabase("allenai", "barrons", "question");
		ModuleClient.createOnDatabase("allenai", "barrons", "knowledge");
		ModuleClient.createOnDatabase("allenai", "barrons", "dependency");
		ModuleClient.createOnDatabase("allenai", "barrons", "plan");
		ModuleClient.createOnDatabase("allenai", "barrons", "job");

		
		
//		ModulesClient.addDefault(p.ns, p.proj);
//		
//		ModulesClient.add(p.ns, p.proj, "lang-frame");
		
	}
	
}
