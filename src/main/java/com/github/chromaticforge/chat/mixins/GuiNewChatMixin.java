package com.github.chromaticforge.chat.mixins;

import com.github.chromaticforge.chat.hook.ChatHooks;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public class GuiNewChatMixin {
    @Inject(method = "printChatMessageWithOptionalDeletion", at = @At("HEAD"), cancellable = true)
    private void chat$removeEmpty(IChatComponent chatComponent, int chatLineId, CallbackInfo ci) {
        if (ChatHooks.shouldRemove(chatComponent)) ci.cancel();
    }

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    private int chat$textShadow(FontRenderer instance, String text, float x, float y, int color) {
        return ChatHooks.drawText(text, x, y, color);
    }

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void chat$backgroundColor(int left, int top, int right, int bottom, int color) {
        ChatHooks.drawBackground(left, top, right, bottom, color);
    }
}
