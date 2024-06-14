package com.natamus.justmobheads.events;

import com.mojang.authlib.GameProfile;
import com.natamus.justmobheads.config.ConfigHandler;
import com.natamus.justmobheads.util.HeadData;
import com.natamus.justmobheads.util.MobHeads;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class HeadDropEvent {	
	public static void mobItemDrop(Level level, Entity entity, DamageSource damageSource) {
		if (level.isClientSide) {
			return;
		}
		
		if (ConfigHandler.onlyDropHeadsByChargedCreeper || ConfigHandler.onlyDropHeadsByPlayerKill) {
			Entity sourceentity = damageSource.getDirectEntity();
			if (ConfigHandler.onlyDropHeadsByChargedCreeper) {
				if (sourceentity instanceof Creeper) {
					Creeper creeper = (Creeper)sourceentity;
					if (!creeper.isPowered()) {
						return;
					}
				}
				else {
					return;
				}
			}
			else if (ConfigHandler.onlyDropHeadsByPlayerKill) {
				if (!(sourceentity instanceof Player)) {
					return;
				}
			}
		}
		
		if (ConfigHandler.onlyAdultMobsDropTheirHead) {
			if (entity instanceof TamableAnimal) {
				TamableAnimal te = (TamableAnimal)entity;
				if (te.isBaby()) {
					return;
				}
			}
		}
		
		String mobname = MobHeads.getName(entity);
		if (mobname.equals("")) {
			return;
		}
		
		double extrachance = 0;
		Entity source = damageSource.getEntity();
		if (ConfigHandler.enableLootingEnchant && source instanceof Player) {
			int looting = EnchantmentHelper.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOOTING), (LivingEntity)source);
			if (looting > 0) {
				extrachance = 0.025 + (looting/100F);
			}
		}
		
		String headname = "";
		if (mobname.equals("creeper") || mobname.equals("zombie") || mobname.equals("skeleton")) {
			if (ConfigHandler.enableStandardHeads) {
				headname = mobname.substring(0, 1).toUpperCase() + mobname.substring(1) + " Head";
			}
			else {
				return;
			}
		}
		
		double num = Math.random();
		if (ConfigHandler.mobSpecificDropChances) {
			double chance = -1;
			if (headname.equals("")) {
				if (HeadData.headchances.containsKey(mobname)) {
					chance = HeadData.headchances.get(mobname);
				}
			}
			else {
				chance = ConfigHandler.creeperSkeletonZombieDropChance;
			}

			if (chance == -1) {
				if (num > ConfigHandler.overallDropChance + extrachance) {
					return;
				}
			}
			else if (num > chance + extrachance) {
				return;
			}
		}
		else if (num > ConfigHandler.overallDropChance + extrachance) {
			return;
		}
		
		BlockPos pos = entity.blockPosition();
		ItemEntity mobhead;
		if (headname.equals("")) {
			ItemStack headstack = MobHeads.getMobHead(mobname, 1);
			if (headstack == null) {
				return;
			}
			
			mobhead = new ItemEntity(level, pos.getX(), pos.getY()+1, pos.getZ(), headstack);
		}
		else {
			mobhead = new ItemEntity(level,pos.getX(), pos.getY()+1, pos.getZ(), MobHeads.getStandardHead(headname));
		}
		
		level.addFreshEntity(mobhead);
	}

    public static void onItemPickup(Level level, Player player, ItemStack itemStack) {
        if (level.isClientSide) {
            return;
        }

		Item item = itemStack.getItem();
        if (!(item instanceof PlayerHeadItem)) {
            return;
        }

		if (itemStack.has(DataComponents.CUSTOM_NAME)) {
			return;
		}

		ResolvableProfile resolvableProfile = itemStack.get(DataComponents.PROFILE);
		if (resolvableProfile == null) {
			return;
		}

		GameProfile gameProfile = resolvableProfile.gameProfile();
		if (gameProfile == null) {
			return;
		}

		String headName = gameProfile.getName();
		if (headName == null) {
			return;
		}

		if (headName.isEmpty()) {
			return;
		}

		if (!HeadData.headdata.containsKey(headName.toLowerCase().replace(" ", "_"))) {
			return;
		}

		itemStack.set(DataComponents.CUSTOM_NAME, Component.literal(headName + " Head"));
    }
}
