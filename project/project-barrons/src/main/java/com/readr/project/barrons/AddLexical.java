package com.readr.project.barrons;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.readr.model.module.lexical.LexicalUnit;
import com.readr.model.module.lexical.LexicalUnitForm;
import com.readr.model.module.lexical.LexicalUnitFormDimension;
import com.readr.model.module.lexical.LexicalUnitFormDimensionValue;
import com.readr.model.module.lexical.LexicalUnitType;
import com.readr.service.db.client.ReadrDBClient;

public class AddLexical {
	static String dir = "/Users/raphael/scratch4";
	
	static String ns, proj;
	static ReadrDBClient dbClient;
	
	public static void main(String[] args) throws Exception {
		Properties properties = getProperties();
		ns = properties.getProperty("ns");
		proj = properties.getProperty("proj");
		dbClient = new ReadrDBClient();
		
		BufferedReader r1 = new BufferedReader(new InputStreamReader
				(new FileInputStream(dir + "/verbs.forms.inflated")));
		String l = null;

		// read blocks with same id
		String lastId = null;
		List<String[]> block = new ArrayList<String[]>();
		while ((l = r1.readLine()) != null) {
			String[] t = l.split("\t");
			String id = t[0];
			
			if (lastId == null ||
					!lastId.equals(id)) {
				if (lastId != null)
					addLexicalUnit(lastId, block);
				lastId = id;
				block.clear();
			}
			
			block.add(t);			
		}
		if (lastId != null)
			addLexicalUnit(lastId, block);
		r1.close();
	}
	
	private static void addLexicalUnit(String id, List<String[]> block) {
		List<LexicalUnitForm> forms = new ArrayList<LexicalUnitForm>();
		LexicalUnit lu = new LexicalUnit(-1, "", LexicalUnitType.Verb, forms);
		dbClient.meaning.lexical.add(ns, proj, lu);
		
		for (String[] t : block) {
			List<LexicalUnitFormDimensionValue> dvs = new ArrayList<LexicalUnitFormDimensionValue>();
			dvs.add(new LexicalUnitFormDimensionValue(-1, LexicalUnitFormDimension.VerbType, t[2]));
			dvs.add(new LexicalUnitFormDimensionValue(-1, LexicalUnitFormDimension.Person, t[3]));
			dvs.add(new LexicalUnitFormDimensionValue(-1, LexicalUnitFormDimension.Number, t[4]));
			dvs.add(new LexicalUnitFormDimensionValue(-1, LexicalUnitFormDimension.Tense, t[5]));
			LexicalUnitForm f = new LexicalUnitForm(-1, lu.lexicalID, t[1], dvs);

			//forms.add(f);
			
			dbClient.meaning.lexicalForm.add(ns, proj, f);
		}
		
	}
	
	private static Properties getProperties() throws Exception {
		Properties properties = new Properties();				
		InputStream stream = AddBarrons.class.getResourceAsStream("project.properties");
		properties.load(stream);
		return properties;
	}
}
