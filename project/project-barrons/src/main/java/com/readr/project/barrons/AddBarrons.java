package com.readr.project.barrons;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.readr.model.module.text.Text;
import com.readr.service.db.client.ReadrDBClient;
import com.readr.service.interactive.client.ReadrInteractiveClient;

public class AddBarrons {
	static String dir = "/Users/raphael/allenai/barrons-4th-grade";
	
	public static void main(String[] args) throws Exception {
		Properties properties = getProperties();
		String ns = properties.getProperty("ns");
		String proj = properties.getProperty("proj");
		ReadrDBClient dbClient = new ReadrDBClient();
		ReadrInteractiveClient interactiveClient = new ReadrInteractiveClient();
		
		Map<String,String> headers = new HashMap<String,String>();
		{
			BufferedReader r1 = new BufferedReader(new InputStreamReader
					(new FileInputStream(dir + "/Barrons-Grade-4-Science-headers.txt")));
			String l = null;
			while ((l = r1.readLine()) != null) {
				String[] t = l.split("\t");
				headers.put(t[0], t[1]);
			}
			r1.close();
		}
		
		// call service-interactive??
		BufferedReader r2 = new BufferedReader(new InputStreamReader
				(new FileInputStream(dir + "/Barrons-Grade-4-Science-sentences.txt")));
		String l = null;
		String curSec = null;
		String curPar = null;
		StringBuilder sb = new StringBuilder();
		
		while ((l = r2.readLine()) != null) {
			String[] t = l.split("\t");
			String label = t[0];
			String sentence = t[1];
			int li = label.lastIndexOf(".");
			int lj = label.substring(0, li).lastIndexOf(".");
			String sec = label.substring(0, lj);
			String par = label.substring(lj+1, li);
			
			System.out.println("label    " + label);
			System.out.println("   par   " + par);
			System.out.println("   sec   " + sec);

//			Text te = new Text(-1, sentence);
//			System.out.println(te.text);
//			TextClient.add(ns,  proj, te);
//			InteractiveClient.setContentAndProcess(ns, proj, te.documentID, sentence);

			if (curSec == null || !curSec.equals(sec)) {
				if (curSec != null) {
					// write section as document to db
					String text = sb.toString();
					text += "\n";
					Text te = new Text(-1, text);
					dbClient.text.document.add(ns, proj, te);
					interactiveClient.run.setContentAndProcess(ns, proj, te.documentID, text);					
				}
				// now create new document
				sb.setLength(0);
				sb.append(headers.get(sec));
				sb.append("\n\n");
				curPar = null;
				curSec = sec;
			}
			
			if (curPar == null || !curPar.equals(par)) {
				if (curPar != null) {
					sb.append("\n\n");
				}
				curPar = par;
			}
			
			sb.append(sentence);
			sb.append(" ");
		}
		r2.close();
	}	

	private static Properties getProperties() throws Exception {
		Properties properties = new Properties();				
		InputStream stream = AddBarrons.class.getResourceAsStream("project.properties");
		properties.load(stream);
		return properties;
	}
}
