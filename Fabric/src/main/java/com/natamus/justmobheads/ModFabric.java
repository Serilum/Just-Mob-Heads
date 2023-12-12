package com.natamus.justmobheads;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectiveEntityEvents;
import com.natamus.justmobheads.cmds.CommandJmh;
import com.natamus.justmobheads.events.HeadDropEvent;
import com.natamus.justmobheads.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CollectiveEntityEvents.ON_ENTITY_IS_DROPPING_LOOT.register((Level world, Entity entity, DamageSource damageSource) -> {
			HeadDropEvent.mobItemDrop(world, entity, damageSource);
		});

		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
			return HeadDropEvent.onPlayerHeadBreak(world, player, pos, state, entity);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			CommandJmh.register(dispatcher);
		});
	}

	private static void setGlobalConstants() {

	}
}
