package com.natamus.justmobheads;

import com.natamus.justmobheads.config.ConfigHandler;
import com.natamus.justmobheads.util.HeadData;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		HeadData.init();
	}
}