package com.serilum.justmobheads.neoforge.events;

import com.serilum.justmobheads.cmds.CommandJmh;
import com.serilum.justmobheads.events.HeadDropEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

public class NeoForgeHeadDropEvent {
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
	public static void onItemPickup(ItemEntityPickupEvent.Post e) {
		Player player = e.getPlayer();
		HeadDropEvent.onItemPickup(player.level(), player, e.getItemEntity().getItem());
	}
}
