package com.natamus.justmobheads.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.justmobheads.cmds.CommandJmh;
import com.natamus.justmobheads.events.HeadDropEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeHeadDropEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	CommandJmh.register(e.getDispatcher());
    }

	@SubscribeEvent
	public void mobItemDrop(LivingDropsEvent e) {
		LivingEntity livingEntity = e.getEntityLiving();
		HeadDropEvent.mobItemDrop(livingEntity.level, livingEntity, e.getSource());
	}
	
	@SubscribeEvent
	public void onPlayerHeadBreak(BlockEvent.BreakEvent e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getWorld());
		if (level == null) {
			return;
		}

		if (!HeadDropEvent.onPlayerHeadBreak(level, e.getPlayer(), e.getPos(), e.getState(), null)) {
			e.setCanceled(true);
		}
	}
}
