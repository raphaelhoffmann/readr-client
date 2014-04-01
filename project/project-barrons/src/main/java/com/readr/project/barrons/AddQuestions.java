package com.readr.project.barrons;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.readr.model.module.text.Text;
import com.readr.service.interactive.client.RunClient;
import com.readr.service.db.client.TextClient;

public class AddQuestions {
	static String ns = "John";
	static String proj = "TACKBP";
	
	public static void main(String[] args) throws Exception {
		// call service-interactive??
		BufferedReader r = new BufferedReader
				(new InputStreamReader(new FileInputStream("/Users/raphael/allenai/107qns.txt")));
		r.readLine();
		String l = null;
		int id = 1000;		
		while ((l = r.readLine()) != null) {
			String[] t = l.split("\t");
			String text = t[9];
			//TextStore.create(ctx, t);
			Text te = new Text(id, text);
			TextClient.add(ns,  proj, te);
//			QuestionStore.
			RunClient.setContentAndProcess(ns, proj, te.documentID, text);
			System.out.println(t[9]);
			break;
		}
		r.close();
	}	
}
