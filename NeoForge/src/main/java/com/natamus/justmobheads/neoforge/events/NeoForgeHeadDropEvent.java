package com.natamus.justmobheads.neoforge.events;

import com.natamus.justmobheads.cmds.CommandJmh;
import com.natamus.justmobheads.events.HeadDropEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.EntityItemPickupEvent;

@EventBusSubscriber
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
	public static void onItemPickup(EntityItemPickupEvent e) {
		Player player = e.getEntity();
		HeadDropEvent.onItemPickup(player.level(), player, e.getItem().getItem());
	}
}
