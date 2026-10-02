package com.serilum.justmobheads;

import com.natamus.collective.translations.ServerTranslationPack;
import com.serilum.justmobheads.config.ConfigHandler;
import com.serilum.justmobheads.util.HeadData;
import com.serilum.justmobheads.util.Reference;

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