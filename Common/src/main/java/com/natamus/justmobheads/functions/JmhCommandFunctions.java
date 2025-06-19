package com.natamus.justmobheads.functions;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.natamus.justmobheads.util.HeadData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

public class JmhCommandFunctions {
	public static final SuggestionProvider<CommandSourceStack> mobHeadSuggestions = (context, builder) -> SharedSuggestionProvider.suggest(
		HeadData.headTextureData.keySet(), builder,
        value -> value,
        value -> Component.literal(value)
    );
}
