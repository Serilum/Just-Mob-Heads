package com.natamus.justmobheads;

import com.natamus.collective.translations.ServerTranslationPack;
import com.natamus.justmobheads.config.ConfigHandler;
import com.natamus.justmobheads.util.HeadData;
import com.natamus.justmobheads.util.Reference;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		HeadData.init();

		ServerTranslationPack.requireClientTranslations(Reference.NAME);
	}
}