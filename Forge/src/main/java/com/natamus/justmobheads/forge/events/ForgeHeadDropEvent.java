package com.natamus.justmobheads.forge.events;

import com.natamus.justmobheads.cmds.CommandJmh;
import com.natamus.justmobheads.events.HeadDropEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeHeadDropEvent {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeHeadDropEvent.class);
	}

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
