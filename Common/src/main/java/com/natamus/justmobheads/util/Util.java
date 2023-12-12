package com.natamus.justmobheads.util;

import com.natamus.collective.functions.DataFunctions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Util {
	private static final String dirpath = DataFunctions.getConfigDirectory() + File.separator + "justmobheads";
	private static final File dir = new File(dirpath);
	private static final File file = new File(dirpath + File.separator + "headchances.txt");
	
	public static boolean generateChanceConfig(Map<String, Double> defaultchances) throws IOException {
		HeadData.headchances = new HashMap<String, Double>();
		
		PrintWriter writer;
		if (!dir.isDirectory() || !file.isFile()) {
			if (!dir.mkdirs()) {
				return false;
			}
			writer = new PrintWriter(dirpath + File.separator + "headchances.txt", StandardCharsets.UTF_8);
		}
		else {
			String content = new String(Files.readAllBytes(Paths.get(dirpath + File.separator + "headchances.txt", new String[0])));
			String[] cspl = content.replaceAll("\n", "").split(",");
			for (String line : cspl) {
				String[] linespl = line.replace("\"", "").replaceAll(" ", "").trim().split(":");
				if (linespl.length < 2) {
					continue;
				}
				
				String mobname = linespl[0];
				double chancevalue;
				try {
					chancevalue = Double.parseDouble(linespl[1]);
				}
				catch (NumberFormatException ex) {
					continue;
				}
				
				HeadData.headchances.put(mobname, chancevalue);
			}
			
			List<String> appendkeys = new ArrayList<String>();
			
			List<String> defaultkeys = new ArrayList<String>(defaultchances.keySet());
			Collections.sort(defaultkeys);
			for (String mobname : defaultkeys) {
				if (!HeadData.headchances.containsKey(mobname)) {
					appendkeys.add(mobname);
				}
			}
			
			if (appendkeys.size() > 0) {
				writer = new PrintWriter(new BufferedWriter(new FileWriter(dirpath + File.separator + "headchances.txt", true)));
				
				Collections.sort(appendkeys);
				for (String mobname : appendkeys) {
					Double chancevalue = defaultchances.get(mobname);
					
					writer.println('"' + mobname + '"' + " : " + chancevalue + ",");
					HeadData.headchances.put(mobname, chancevalue);
				}
				
				writer.close();
			}
			
			return true;
		}
		
		if (writer != null) {
			List<String> defaultkeys = new ArrayList<String>(defaultchances.keySet());
			Collections.sort(defaultkeys);
			for (String mobname : defaultkeys) {
				Double chancevalue = defaultchances.get(mobname);
				
				writer.println('"' + mobname + '"' + " : " + chancevalue + ",");
				HeadData.headchances.put(mobname, chancevalue);
			}
			
			writer.close();
		}
		
		return false;
	}
}
