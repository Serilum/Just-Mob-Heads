package com.natamus.justmobheads.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.justmobheads.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean mobSpecificDropChances = true;
	@Entry public static boolean enableStandardHeads = false;
	@Entry public static boolean enableLootingEnchant = true;
	@Entry public static boolean onlyAdultMobsDropTheirHead = true;
	@Entry(min = 0.0001, max = 1.0) public static double overallDropChance = 0.1;
	@Entry(min = 0.0001, max = 1.0) public static double creeperSkeletonZombieDropChance = 0.1;
	@Entry public static boolean onlyDropHeadsByChargedCreeper = false;
	@Entry public static boolean onlyDropHeadsByPlayerKill = false;

	public static void initConfig() {
		configMetaData.put("mobSpecificDropChances", Arrays.asList(
			"If enabled, overrides the 'overallDropChance' variable with the specific values."
		));
		configMetaData.put("enableStandardHeads", Arrays.asList(
			"If enabled, allows Creepers, Skeletons and Zombies to drop their heads."
		));
		configMetaData.put("enableLootingEnchant", Arrays.asList(
			"If enabled, the looting enchant will have an effect on the drop chance."
		));
		configMetaData.put("onlyAdultMobsDropTheirHead", Arrays.asList(
			"If enabled, only adult tameable mobs will have a chance to drop their head on death."
		));
		configMetaData.put("overallDropChance", Arrays.asList(
			"Sets the chance of a mob dropping its head if 'mobSpecificDropChances' is disabled."
		));
		configMetaData.put("creeperSkeletonZombieDropChance", Arrays.asList(
			"Sets head drop chance for Zombies, Skeletons and Creepers if 'enableStandardHeads' is enabled."
		));
		configMetaData.put("onlyDropHeadsByChargedCreeper", Arrays.asList(
			"When enabled, only drops mob heads if the source on death is a charged creeper. This overwrites the onlyDropHeadsByPlayerKill value."
		));
		configMetaData.put("onlyDropHeadsByPlayerKill", Arrays.asList(
			"When enabled, only drops mob heads if the source on death is from a player."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}