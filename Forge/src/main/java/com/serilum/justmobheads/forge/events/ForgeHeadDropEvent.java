package com.serilum.justmobheads.forge.events;

import com.serilum.justmobheads.cmds.CommandJmh;
import com.serilum.justmobheads.events.HeadDropEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeHeadDropEvent {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent e) {
		CommandJmh.register(e.getDispatcher());
	}

	@SubscribeEvent
	public static void mobItemDrop(LivingDropsEvent e) {
		LivingEntity livingEntity = e.getEntity();
		HeadDropEvent.mobItemDrop(livingEntity.level(), livingEntity, e.getSource());
	}
	
	@SubscribeEvent
	public static void onItemPickup(EntityItemPickupEvent e) {
		Player player = e.getEntity();
		HeadDropEvent.onItemPickup(player.level(), player, e.getItem().getItem());
	}
}
