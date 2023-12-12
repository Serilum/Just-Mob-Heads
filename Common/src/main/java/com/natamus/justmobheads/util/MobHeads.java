package com.natamus.justmobheads.util;

import com.mojang.datafixers.util.Pair;
import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.HeadFunctions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.List;

public class MobHeads {
	static List<String> horsetypes = Arrays.asList("white", "creamy", "chestnut", "brown", "black", "gray", "dark_brown");
	static List<String> llamatypes = Arrays.asList("creamy", "white", "brown", "gray");
	static List<String> parrottypes = Arrays.asList("red", "blue", "green", "cyan", "gray");
	static List<String> rabbittypes = Arrays.asList("brown", "white", "black", "black_and_white", "gold", "salt_and_pepper");
	static List<String> cattypes = Arrays.asList("tabby", "tuxedo", "red", "siamese", "british_shorthair", "calico", "persian", "ragdoll", "white", "jellie", "black");
	static List<String> axolotltypes = Arrays.asList("lucy", "wild", "gold", "cyan", "blue");
	
	public static ItemStack getMobHead(String mobname, Integer amount) {
		Pair<String, String> mobdata = HeadData.headdata.get(mobname);
		if (mobdata == null) {
			return null;
		}
		
		String headname = capitalizeFirst(mobname.replace("_", " ")) + " Head";
		String oldid = mobdata.getFirst();
		String texture = mobdata.getSecond();

		return HeadFunctions.getTexturedHead(headname, texture, oldid, amount);
	}
	
	public static ItemStack getStandardHead(String headname) {
		ItemStack mobhead = new ItemStack(Items.PLAYER_HEAD, 1);
		String mob = headname.toLowerCase().split(" ")[0];
		switch (mob) {
			case "creeper" -> mobhead = new ItemStack(Items.CREEPER_HEAD, 1);
			case "zombie" -> mobhead = new ItemStack(Items.ZOMBIE_HEAD, 1);
			case "skeleton" -> mobhead = new ItemStack(Items.SKELETON_SKULL, 1);
		}
		
		mobhead.setHoverName(Component.literal(headname));
		return mobhead;
	}
	
	public static String getName(Entity entity) {
		String entitystring = EntityFunctions.getEntityString(entity);
		String mobname = entitystring.split("\\[")[0].replace("Entity", "");
		mobname = String.join("_", mobname.split("(?<=.)(?=\\p{Lu})")).toLowerCase();
		
		if (entity instanceof Creeper) {
			Creeper creeper = (Creeper)entity;
			if (creeper.isPowered()) {
				mobname = "charged_creeper";
			}
		}
		else if (entity instanceof Cat) {
			Cat cat = (Cat)entity;

			CatVariant variant = cat.getVariant();

			ResourceLocation texture = variant.texture();
			String type = texture.toString().split("cat/")[1].replace(".png", "");
			mobname = type + "_cat";
		}
		else if (entity instanceof Horse) {
			Horse horse = (Horse)entity;
			int type = horse.getVariant().getId(); // horse.getHorseVariant();
			
			if (type >= 1024) {
				type -= 1024;
			}
			else if (type >= 768) {
				type -= 768;
			}
			else if (type >= 512) {
				type -= 512;
			}
			else if (type >= 256) {
				type -= 256;
			}
			mobname = horsetypes.get(type) + "_horse";
		}
		else if (entity instanceof Llama) {
			Llama llama = (Llama)entity;
			int type = llama.getVariant().getId();
			if (type < llamatypes.size()) {
				mobname = llamatypes.get(type) + "_" + mobname;
			}
		}
		else if (entity instanceof TraderLlama) {
			TraderLlama traderllama = (TraderLlama)entity;
			int type = traderllama.getVariant().getId();
			if (type < llamatypes.size()) {
				mobname = llamatypes.get(type) + "_trader_" + mobname;
			}
		}
		else if (entity instanceof Parrot) {
			Parrot parrot = (Parrot)entity;
			int type = parrot.getVariant().getId();
			if (type < parrottypes.size()) {
				mobname = parrottypes.get(type) + "_parrot";
			}
		}
		else if (entity instanceof Rabbit) {
			Rabbit rabbit = (Rabbit)entity;
			int type = rabbit.getVariant().id();
			String rabbitname = rabbit.getDisplayName().getString();
			if (rabbitname.equals("Toast")) {
				mobname = "toast_rabbit";
			}
			else {
				if (type < rabbittypes.size()) {
					mobname = rabbittypes.get(type) + "_rabbit";
				}
				else if (type == 99) {
					mobname = "killer_rabbit";
				}
			}
		}
		else if (entity instanceof Sheep) {
			Sheep sheep = (Sheep)entity;
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
		else if (entity instanceof MushroomCow) {
			MushroomCow mooshroom = (MushroomCow)entity;
			if (mooshroom.getVariant() == MushroomCow.MushroomType.BROWN) {
				mobname = "brown_mooshroom";
			}	
		}
		else if (entity instanceof Fox) {
			Fox fox = (Fox)entity;
			if (fox.getVariant() == Fox.Type.SNOW) {
				mobname = "snow_fox";
			}
			else {
				mobname = "red_fox";
			}
		}
		else if (entity instanceof Axolotl) {
			Axolotl axolotl = (Axolotl)entity;
			int type = axolotl.getVariant().getId();
			if (type < axolotltypes.size()) {
				mobname = axolotltypes.get(type) + "_axolotl";
			}
		}
		else if (entity instanceof Villager) {
			Villager villager = (Villager)entity;
			
			VillagerData d = villager.getVillagerData();
			VillagerProfession prof = d.getProfession();
			if (!prof.toString().equals("none")) {
				mobname = prof.toString();
			}
		}
		else if (entity instanceof ZombieVillager) {
			ZombieVillager zombievillager = (ZombieVillager)entity;

			VillagerData d = zombievillager.getVillagerData();
			VillagerProfession prof = d.getProfession();
			if (!prof.toString().equals("none")) {
				mobname = "zombie_" + prof;
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
