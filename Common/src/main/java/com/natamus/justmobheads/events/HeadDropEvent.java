package com.natamus.justmobheads.events;

import com.google.common.base.Strings;
import com.mojang.authlib.GameProfile;
import com.natamus.justmobheads.config.ConfigHandler;
import com.natamus.justmobheads.util.HeadData;
import com.natamus.justmobheads.util.MobHeads;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class HeadDropEvent {	
	public static void mobItemDrop(Level world, Entity entity, DamageSource damageSource) {
		if (world.isClientSide) {
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
			int looting = EnchantmentHelper.getMobLooting((LivingEntity)source);
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
			
			mobhead = new ItemEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), headstack);
		}
		else {
			mobhead = new ItemEntity(world,pos.getX(), pos.getY()+1, pos.getZ(), MobHeads.getStandardHead(headname));
		}
		
		world.addFreshEntity(mobhead);
	}
	
    public static void onItemPickup(Level level, Player player, ItemStack itemStack) {
        if (level.isClientSide) {
            return;
        }

		Item item = itemStack.getItem();
        if (!(item instanceof PlayerHeadItem)) {
            return;
        }

		String headName = "";
		CompoundTag compoundTagItemStack = itemStack.getTag();
		if (compoundTagItemStack == null) {
			return;
		}

		if (compoundTagItemStack.contains("SkullOwner", 8)) {
			headName = compoundTagItemStack.getString("SkullOwner");
		} else if (compoundTagItemStack.contains("SkullOwner", 10)) {
			CompoundTag compoundTagSkullOwner = compoundTagItemStack.getCompound("SkullOwner");
			if (compoundTagSkullOwner.contains("Name", 8)) {
				headName = compoundTagSkullOwner.getString("Name");
			}
		}

		if (headName.isEmpty()) {
			return;
		}

		if (!HeadData.headdata.containsKey(headName.toLowerCase().replace(" ", "_"))) {
			return;
		}

		itemStack.setHoverName(Component.literal(headName + " Head"));
    }

	// Legacy code for previously generated mob heads. Might be removed in the future.
	public static boolean onPlayerHeadBreak(Level world, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity) {
		Block block = state.getBlock();
		if (block instanceof SkullBlock || block instanceof WallSkullBlock) {
			if (player.isCreative()) {
				return true;
			}

			BlockEntity targetBlockEntity = world.getBlockEntity(pos);
			if (!(targetBlockEntity instanceof SkullBlockEntity)) {
				return true;
			}

			SkullBlockEntity sbe = (SkullBlockEntity)targetBlockEntity;
			if (sbe == null) {
				return true;
			}

			String headName = "";
			CompoundTag compoundTagItemStack = targetBlockEntity.getUpdateTag();
			if (compoundTagItemStack.contains("SkullOwner", 8)) {
				headName = compoundTagItemStack.getString("SkullOwner");
			} else if (compoundTagItemStack.contains("SkullOwner", 10)) {
				CompoundTag compoundTagSkullOwner = compoundTagItemStack.getCompound("SkullOwner");
				if (compoundTagSkullOwner.contains("Name", 8)) {
					headName = compoundTagSkullOwner.getString("Name");
				}
			}

			if (!headName.isEmpty()) {
				return true;
			}

			GameProfile profile = sbe.getOwnerProfile();
			if (profile == null) {
				return true;
			}

			if (!Strings.isNullOrEmpty(profile.getName())) {
				return true;
			}

			UUID uuid = profile.getId();
			if (uuid == null) {
				return true;
			}

			String headid = uuid.toString();

			String correctheadname = "";
			for (String headname : HeadData.headdata.keySet()) {
				String headnameid = HeadData.headdata.get(headname).getFirst();
				if (headid.equals(headnameid)) {
					correctheadname = headname;
					break;
				}
			}

			ItemStack named_headstack = MobHeads.getMobHead(correctheadname, 1);
			if (named_headstack != null ) {
				world.destroyBlock(pos, false);

				world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY()+0.5, pos.getZ(), named_headstack));
				return false;
			}
		}

		return true;
	}
}
