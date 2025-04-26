package com.github.chromaticforge.chat.mixins;

import com.github.chromaticforge.chat.hook.ChatHooks;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiChat.class)
public class GuiChatMixin {
    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiChat;drawRect(IIIII)V"))
    private void chat$backgroundColor(int left, int top, int right, int bottom, int color) {
        ChatHooks.drawBackground(left, top, right, bottom, color);
    }
}
