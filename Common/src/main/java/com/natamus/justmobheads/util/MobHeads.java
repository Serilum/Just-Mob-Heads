package com.natamus.justmobheads.util;

import com.mojang.datafixers.util.Pair;
import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.HeadFunctions;
import com.natamus.collective.functions.MessageFunctions;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.animal.chicken.ChickenVariant;
import net.minecraft.world.entity.animal.chicken.ChickenVariants;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.cow.CowVariants;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.entity.animal.equine.TraderLlama;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.frog.FrogVariants;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.animal.pig.PigVariants;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.WeatheringCopper;

import java.util.Arrays;
import java.util.List;

public class MobHeads {
	static List<String> horsetypes = Arrays.asList("white", "creamy", "chestnut", "brown", "black", "gray", "dark_brown");
	static List<String> llamatypes = Arrays.asList("creamy", "white", "brown", "gray");
	static List<String> parrottypes = Arrays.asList("red", "blue", "green", "cyan", "gray");
	static List<String> rabbittypes = Arrays.asList("brown", "white", "black", "black_and_white", "gold", "salt_and_pepper");
	static List<String> cattypes = Arrays.asList("tabby", "tuxedo", "red", "siamese", "british_shorthair", "calico", "persian", "ragdoll", "white", "jellie", "black");
	static List<String> axolotltypes = Arrays.asList("lucy", "wild", "gold", "cyan", "blue");
	
	public static ItemStack getMobHead(String mobName, Integer amount) {
		Pair<String, String> textureData = HeadData.headTextureData.get(mobName);
		if (textureData == null) {
			return null;
		}

		String headNoteBlockSound = HeadData.headNoteBlockSounds.get(mobName) == null ? "" : HeadData.headNoteBlockSounds.get(mobName);
		String formattedMobName = capitalizeFirst(mobName.replace("_", " "));

		String oldid = textureData.getFirst();
		String texture = textureData.getSecond();

		ItemStack texturedHeadStack = HeadFunctions.getNewTexturedHead(mobName, texture, oldid, headNoteBlockSound, amount);
		texturedHeadStack.set(DataComponents.CUSTOM_NAME, MessageFunctions.getTranslatableComponent("collective.justmobheads.gui.head", formattedMobName));

		return texturedHeadStack;
	}
	
	public static ItemStack getStandardHead(String headname) {
		ItemStack mobhead = new ItemStack(Items.PLAYER_HEAD, 1);
		String mob = headname.toLowerCase().split(" ")[0];
		switch (mob) {
			case "creeper" -> mobhead = new ItemStack(Items.CREEPER_HEAD, 1);
			case "zombie" -> mobhead = new ItemStack(Items.ZOMBIE_HEAD, 1);
			case "skeleton" -> mobhead = new ItemStack(Items.SKELETON_SKULL, 1);
		}
		
		mobhead.set(DataComponents.CUSTOM_NAME, Component.literal(headname));
		return mobhead;
	}
	
	public static String getName(Entity entity) {
		String entitystring = EntityFunctions.getEntityString(entity);
		String mobname = entitystring.split("\\[")[0].replace("Entity", "");
		mobname = String.join("_", mobname.split("(?<=.)(?=\\p{Lu})")).toLowerCase();

		switch (entity) {
			case Creeper creeper -> {
				if (creeper.isPowered()) {
					mobname = "charged_creeper";
				}
			}
			case Cat cat -> {
				CatVariant catVariant = cat.getVariant().value();

				Identifier texture = catVariant.assetInfo(false).texturePath();;
				String type = texture.toString().split("cat/")[1].replace(".png", "");
				mobname = type + "_cat";
			}
			case CopperGolem copperGolem -> {
				WeatheringCopper.WeatherState weatherState = copperGolem.getWeatherState();
				String weatherStateString = weatherState.name().toLowerCase();
				if (weatherStateString.equals("unaffected")) {
					weatherStateString = "";
				}
				else {
					weatherStateString += "_";
				}

				mobname = weatherStateString + "copper_golem";
			}
			case Wolf wolf -> {
				Identifier texture = wolf.getTexture();
				String type = texture.toString().split("wolf/")[1].replace(".png", "").replace("wolf_", "");
				mobname = type + "_wolf";
			}
			case Horse horse -> {
				int type = horse.getVariant().getId(); // horse.getHorseVariant();

				if (type >= 1024) {
					type -= 1024;
				} else if (type >= 768) {
					type -= 768;
				} else if (type >= 512) {
					type -= 512;
				} else if (type >= 256) {
					type -= 256;
				}
				mobname = horsetypes.get(type) + "_horse";
			}
			case TraderLlama traderllama -> {
				int type = traderllama.getVariant().getId();
				if (type < llamatypes.size()) {
					mobname = llamatypes.get(type) + "_trader_" + mobname;
				}
			}
			case Llama llama -> {
				int type = llama.getVariant().getId();
				if (type < llamatypes.size()) {
					mobname = llamatypes.get(type) + "_" + mobname;
				}
			}
			case Parrot parrot -> {
				int type = parrot.getVariant().getId();
				if (type < parrottypes.size()) {
					mobname = parrottypes.get(type) + "_parrot";
				}
			}
			case Rabbit rabbit -> {
				int type = rabbit.getVariant().id();
				String rabbitname = rabbit.getDisplayName().getString();
				if (rabbitname.equals("Toast")) {
					mobname = "toast_rabbit";
				} else {
					if (type < rabbittypes.size()) {
						mobname = rabbittypes.get(type) + "_rabbit";
					} else if (type == 99) {
						mobname = "killer_rabbit";
					}
				}
			}
			case Sheep sheep -> {
				boolean checktype = true;
				if (sheep.hasCustomName()) {
					String name = sheep.getName().getString();
					if (name.equals("jeb_")) {
						mobname = "jeb_sheep";
						checktype = false;
					}
				}

				if (checktype) {
					DyeColor type = sheep.getColor();
					mobname = type.toString().toLowerCase() + "_sheep";
				}
			}
			case MushroomCow mooshroom -> {
				if (mooshroom.getVariant() == MushroomCow.Variant.BROWN) {
					mobname = "brown_mooshroom";
				}
			}
			case Fox fox -> {
				if (fox.getVariant() == Fox.Variant.SNOW) {
					mobname = "snow_fox";
				} else {
					mobname = "red_fox";
				}
			}
			case Axolotl axolotl -> {
				int type = axolotl.getVariant().getId();
				if (type < axolotltypes.size()) {
					mobname = axolotltypes.get(type) + "_axolotl";
				}
			}
			case Frog frog -> {
				Holder<FrogVariant> frogVariantHolder = frog.getVariant();
				if (frogVariantHolder.is(FrogVariants.WARM)) {
					mobname = "warm_frog";
				} else if (frogVariantHolder.is(FrogVariants.COLD)) {
					mobname = "cold_frog";
				} else if (frogVariantHolder.is(FrogVariants.TEMPERATE)) {
					mobname = "temperate_frog";
				}
			}
			case Chicken chicken -> {
				Holder<ChickenVariant> chickenVariantHolder = chicken.getVariant();
				if (chickenVariantHolder.is(ChickenVariants.WARM)) {
					mobname = "warm_chicken";
				} else if (chickenVariantHolder.is(ChickenVariants.COLD)) {
					mobname = "cold_chicken";
				} else if (chickenVariantHolder.is(ChickenVariants.TEMPERATE)) {
					mobname = "temperate_chicken";
				}
			}
			case Cow cow -> {
				Holder<CowVariant> cowVariantHolder = cow.getVariant();
				if (cowVariantHolder.is(CowVariants.WARM)) {
					mobname = "warm_cow";
				} else if (cowVariantHolder.is(CowVariants.COLD)) {
					mobname = "cold_cow";
				} else if (cowVariantHolder.is(CowVariants.TEMPERATE)) {
					mobname = "temperate_cow";
				}
			}
			case Pig pig -> {
				Holder<PigVariant> pigVariantHolder = pig.getVariant();
				if (pigVariantHolder.is(PigVariants.WARM)) {
					mobname = "warm_pig";
				} else if (pigVariantHolder.is(PigVariants.COLD)) {
					mobname = "cold_pig";
				} else if (pigVariantHolder.is(PigVariants.TEMPERATE)) {
					mobname = "temperate_pig";
				}
			}
			case Villager villager -> {

				VillagerData d = villager.getVillagerData();
				VillagerProfession prof = d.profession().value();
				if (!prof.toString().equals("none")) {
					mobname = prof.toString();
				}
			}
			case ZombieVillager zombievillager -> {

				VillagerData d = zombievillager.getVillagerData();
				VillagerProfession prof = d.profession().value();
				if (!prof.toString().equals("none")) {
					mobname = "zombie_" + prof;
				}
			}
			default -> {
			}
		}

		return mobname.toLowerCase();
	}
	
	public static String capitalizeFirst(String string) {
		StringBuilder sb = new StringBuilder(string);
		for(int i=0; i < sb.length(); i++) {
			if(i == 0 || sb.charAt(i-1) == ' ') {
				sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
			}
		}
		return sb.toString();
	}
}
