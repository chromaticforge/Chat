package com.github.chromaticforge.chat.mixins;

import com.github.chromaticforge.chat.ChatMod;
import com.github.chromaticforge.chat.config.ChatConfig;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiChat.class)
public class GuiChatMixin {
    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiChat;drawRect(IIIII)V"))
    private void chat$backgroundColor(int left, int top, int right, int bottom, int oldcolor) {
        int color = ChatMod.config.enabled ? ChatConfig.background ? ChatConfig.backgroundColor.getRGB() : 0x00000000 : oldcolor;
        Gui.drawRect(left, top, right, bottom, color);
    }
}
