package com.serilum.justmobheads.util;

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
		HeadData.headChances = new HashMap<String, Double>();
		
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
				
				HeadData.headChances.put(mobname, chancevalue);
			}
			
			List<String> appendkeys = new ArrayList<String>();
			
			List<String> defaultkeys = new ArrayList<String>(defaultchances.keySet());
			Collections.sort(defaultkeys);
			for (String mobname : defaultkeys) {
				if (!HeadData.headChances.containsKey(mobname)) {
					appendkeys.add(mobname);
				}
			}
			
			if (appendkeys.size() > 0) {
				writer = new PrintWriter(new BufferedWriter(new FileWriter(dirpath + File.separator + "headchances.txt", true)));
				
				Collections.sort(appendkeys);
				for (String mobname : appendkeys) {
					Double chancevalue = defaultchances.get(mobname);
					
					writer.println('"' + mobname + '"' + " : " + chancevalue + ",");
					HeadData.headChances.put(mobname, chancevalue);
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
				HeadData.headChances.put(mobname, chancevalue);
			}
			
			writer.close();
		}
		
		return false;
	}

	public static String getHeadChances() {
		List<String> headChances = new ArrayList<>();
		for (String line : getHeadChancesFileText().replaceAll("\n", "").split(",")) {
			String[] lineParts = line.replace("\"", "").replaceAll(" ", "").trim().split(":");
			if (lineParts.length < 2 || !isChance(lineParts[1]) || !HeadData.defaultHeadChances.containsKey(lineParts[0])) {
				continue;
			}

			headChances.add("minecraft:" + lineParts[0] + "=" + lineParts[1]);
		}
		return String.join(",", headChances);
	}

	public static String getDefaultHeadChances() {
		List<String> headChances = new ArrayList<>();
		for (String headName : HeadData.defaultHeadChances.keySet()) {
			headChances.add("minecraft:" + headName + "=" + HeadData.defaultHeadChances.get(headName));
		}
		return String.join(",", headChances);
	}

	public static void setHeadChances(String headChancesText) {
		List<String> lines = new ArrayList<>();
		for (String entry : headChancesText.split(",")) {
			String[] entryParts = entry.split("=");
			if (entryParts.length != 2 || !isChance(entryParts[1].strip())) {
				continue;
			}

			lines.add('"' + getHeadName(entryParts[0].strip()) + '"' + " : " + entryParts[1].strip() + ",");
		}

		try {
			PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
			for (String line : lines) {
				writer.println(line);
			}
			writer.close();

			generateChanceConfig(HeadData.defaultHeadChances);
		}
		catch (IOException ignored) { }
	}

	private static String getHeadName(String headId) {
		if (headId.startsWith("minecraft:")) {
			return headId.substring("minecraft:".length());
		}
		return headId;
	}

	private static boolean isChance(String text) {
		try {
			Double.parseDouble(text);
			return true;
		}
		catch (NumberFormatException ignored) {
			return false;
		}
	}

	private static String getHeadChancesFileText() {
		try {
			return new String(Files.readAllBytes(file.toPath()));
		}
		catch (IOException ignored) {
			return "";
		}
	}
}
