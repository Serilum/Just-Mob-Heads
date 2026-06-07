package com.natamus.justmobheads.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.justmobheads.functions.JmhCommandFunctions;
import com.natamus.justmobheads.util.HeadData;
import com.natamus.justmobheads.util.MobHeads;
import com.natamus.justmobheads.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandJmh {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("jmh").requires((iCommandSender) -> iCommandSender.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
			.then(Commands.literal("reload")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();
				MessageFunctions.sendTranslatableMessage(source, "collective.justmobheads.message.reloadingheadchances", ChatFormatting.DARK_GREEN);
				try {
					if (Util.generateChanceConfig(HeadData.defaultHeadChances)) {
						MessageFunctions.sendTranslatableMessage(source, "collective.justmobheads.message.succesfullyloadeddropchances", ChatFormatting.DARK_GREEN);
					}
					else {
						MessageFunctions.sendTranslatableMessage(source, "collective.justmobheads.message.generatedconfigfile", ChatFormatting.DARK_GREEN);
					}
				} catch (Exception ex) {
					MessageFunctions.sendTranslatableMessage(source, "collective.justmobheads.message.somethingwentwrong", ChatFormatting.RED);
				}
				return 1;
			}))
			.then(Commands.literal("head")
			.then(Commands.literal("list")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();
				
				MessageFunctions.sendTranslatableMessage(source, "collective.justmobheads.message.generatefollowingmob", ChatFormatting.DARK_GREEN);
				MessageFunctions.sendTranslatableMessage(source, " ", "collective.justmobheads.message.usagejmhheadname", ChatFormatting.DARK_GREEN);
				
				List<String> mobnames = new ArrayList<String>(HeadData.headTextureData.keySet());
				Collections.sort(mobnames);
				String mnstr = String.join(", ", mobnames);
				MessageFunctions.sendMessage(source, mnstr, ChatFormatting.YELLOW);
				
				return 1;
			})))
			.then(Commands.literal("head")
			.then(Commands.argument("mob-name", StringArgumentType.string()).suggests(JmhCommandFunctions.mobHeadSuggestions)
			.executes((command) -> {
				return headCommand(command, 1);
			})))
			.then(Commands.literal("head")
			.then(Commands.argument("mob-name", StringArgumentType.string()).suggests(JmhCommandFunctions.mobHeadSuggestions)
			.then(Commands.argument("amount", IntegerArgumentType.integer(1, 64))
			.executes((command) -> {
				return headCommand(command, IntegerArgumentType.getInteger(command, "amount"));
			}))))
		);
	}

	private static int headCommand(CommandContext<CommandSourceStack> command, int amount) {
		CommandSourceStack source = command.getSource();
		String mobname = StringArgumentType.getString(command, "mob-name").toLowerCase();

		if (!HeadData.headTextureData.containsKey(mobname)) {
			MessageFunctions.sendTranslatableMessage(source, "collective.justmobheads.message.mobnameexistget", ChatFormatting.RED, mobname);
			MessageFunctions.sendTranslatableMessage(source, " ", "collective.justmobheads.message.usagejmhheadlist", ChatFormatting.RED);
			return 1;
		}

		Player player;
		try {
			player = source.getPlayerOrException();
		}
		catch (CommandSyntaxException ex) {
			MessageFunctions.sendTranslatableMessage(source, "collective.shared.message.playeronly", ChatFormatting.RED);
			return 1;
		}

		ItemStack headstack = MobHeads.getMobHead(mobname, amount);
		if (!player.getInventory().add(headstack)) {
			player.drop(headstack, false);
		}

		String s = "";
		if (amount > 1) {
			s = "s";
		}

		MessageFunctions.sendTranslatableMessage(source, "collective.shared.message.successfullygeneratedhead", ChatFormatting.DARK_GREEN, amount, StringFunctions.capitalizeFirst(mobname.replace("_", " ")));
		return 1;
	}
}
