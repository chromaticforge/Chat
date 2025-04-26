package com.github.chromaticforge.chat.hook

import cc.polyfrost.oneconfig.platform.Platform
import cc.polyfrost.oneconfig.renderer.TextRenderer
import com.github.chromaticforge.chat.ChatMod
import com.github.chromaticforge.chat.config.ChatConfig
import net.minecraft.client.gui.Gui
import net.minecraft.util.IChatComponent

object ChatHooks {
    @JvmStatic
    fun drawBackground(left: Int, top: Int, right: Int, bottom: Int, original: Int) {
        val color = when {
            !ChatMod.config.enabled -> original
            ChatConfig.background -> ChatConfig.backgroundColor.rgb
            else -> 0x00000000
        }

        Gui.drawRect(left, top, right, bottom, color)
    }

    @JvmStatic
    fun drawText(text: String, x: Float, y: Float, color: Int): Int {
        return if (ChatMod.config.enabled) {
            when (ChatConfig.textShadow) {
                0 -> Platform.getGLPlatform().drawText(text, x, y, color, false).toInt()
                2 -> TextRenderer.drawBorderedText(text, x, y, color, (color shr 24) and 0xFF)
                else -> Platform.getGLPlatform().drawText(text, x, y, color, true).toInt()
            }
        } else {
            Platform.getGLPlatform().drawText(text, x, y, color, true).toInt()
        }
    }

    @JvmStatic
    fun shouldRemove(component: IChatComponent): Boolean {
        return if (ChatMod.config.enabled) {
            component.unformattedText.isBlank() && ChatConfig.hideBlankMessages
        } else {
            false
        }
    }
}
