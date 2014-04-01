package com.readr.service.db.client;

import com.readr.model.module.text.Text;


public class Test {
	
	public static void main(String[] args) throws Exception {
		
		ReadrDBClient client = new ReadrDBClient();
		
		client.authenticate("test", "test");
		
		Text t = client.text.document.get("allenai", "barrons", 70);
		
		System.out.println(t.text);
	}
}
