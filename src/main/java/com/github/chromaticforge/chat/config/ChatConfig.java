package com.github.chromaticforge.chat.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Checkbox;
import cc.polyfrost.oneconfig.config.annotations.Color;
import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import com.github.chromaticforge.chat.ChatMod;

public class ChatConfig extends Config {
    @Dropdown(name = "Text Shadow", description = "The type of text shadow.", options = {"No Shadow", "Shadow", "Full Shadow"}, subcategory = "Text")
    public static int textShadow = 1;

    @Checkbox(name = "Background", description = "Toggles the background of the chat.", subcategory = "Background")
    public static boolean background = true;

    @Color(name = "Background Color", description = "The color of the chat's background.", subcategory = "Background")
    public static OneColor backgroundColor = new OneColor(0, 0, 0, 128);

    // TODO: The below
//    @KeyBind(name = "Toggle", description = "Toggles the visibility of the chat.", subcategory = "Controls")
//    public static OneKeyBind toggle = new OneKeyBind(UKeyboard.KEY_R);
//
//    @KeyBind(name = "Peek", description = "Allows you to view the chat while moving around.", subcategory = "Controls")
//    public static OneKeyBind peek = new OneKeyBind(UKeyboard.KEY_Z);

    public ChatConfig() {
        super(new Mod(ChatMod.NAME, ModType.UTIL_QOL), ChatMod.ID + ".json");
        initialize();
    }
}
