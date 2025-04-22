package com.github.chromaticforge.chat.mixins;

import cc.polyfrost.oneconfig.platform.Platform;
import cc.polyfrost.oneconfig.renderer.TextRenderer;
import com.github.chromaticforge.chat.ChatMod;
import com.github.chromaticforge.chat.config.ChatConfig;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiNewChat.class)
public class GuiNewChatMixin {
    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    private int chat$textShadow(FontRenderer instance, String text, float x, float y, int color) {
        if (ChatMod.config.enabled) {
            switch (ChatConfig.textShadow) {
                case 0:
                    return (int) Platform.getGLPlatform().drawText(text, x, y, color, false);
                case 1:
                    return (int) Platform.getGLPlatform().drawText(text, x, y, color, true);
                case 2:
                    return TextRenderer.drawBorderedText(text, x, y, color, (color >> 24) & 0xFF);
            }
        }

        return instance.drawString(text, x, y, color, true);
    }

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void chat$backgroundColor(int left, int top, int right, int bottom, int oldcolor) {
        int color = ChatMod.config.enabled ? ChatConfig.background ? ChatConfig.backgroundColor.getRGB() : 0x00000000 : oldcolor;
        Gui.drawRect(left, top, right, bottom, color);
    }
}
