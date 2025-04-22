package com.github.chromaticforge.chat;

import com.github.chromaticforge.chat.config.ChatConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = ChatMod.ID,
        name = ChatMod.NAME,
        version = ChatMod.VERSION
)
public class ChatMod {
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static ChatConfig config;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new ChatConfig();
    }
}
