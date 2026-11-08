package com.serilum.justmobheads;

import com.natamus.collective.config.DuskConfigCustomFile;
import com.natamus.collective.translations.ServerTranslationPack;
import com.serilum.justmobheads.config.ConfigHandler;
import com.serilum.justmobheads.util.HeadData;
import com.serilum.justmobheads.util.Reference;
import com.serilum.justmobheads.util.Util;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		HeadData.init();
		DuskConfigCustomFile.addFixedIdValues(Reference.MOD_ID, "headchances.txt", "Head Chances", "The chance a mob drops its head, from 0 to 1. Set it to 0 to turn a head off.", "entity_type", Util::getHeadChances, Util::setHeadChances);
		DuskConfigCustomFile.setDefaults(Reference.MOD_ID, "headchances.txt", Util::getDefaultHeadChances);

		ServerTranslationPack.requireClientTranslations(Reference.NAME);
	}
}