package com.github.chromaticforge.chat

import com.github.chromaticforge.chat.config.ChatConfig
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(
    modid = ChatMod.ID,
    name = ChatMod.NAME,
    version = ChatMod.VERSION,
    modLanguageAdapter = "cc.polyfrost.oneconfig.utils.KotlinLanguageAdapter"
)
object ChatMod {
    const val ID = "@MOD_ID@"
    const val NAME = "@MOD_NAME@"
    const val VERSION = "@MOD_VERSION@"

    lateinit var config: ChatConfig

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        config = ChatConfig

        //#if MC==10809
        MinecraftForge.EVENT_BUS.register(this)
        //#endif
    }

    @SubscribeEvent
    fun renderChat(event: RenderGameOverlayEvent.Chat) {
        if (ChatConfig.chatPosition) {
            event.posY -= 12
        }
    }
}
