package com.github.chromaticforge.chat.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Checkbox
import cc.polyfrost.oneconfig.config.annotations.Color
import cc.polyfrost.oneconfig.config.annotations.Dropdown
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.core.OneColor
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import com.github.chromaticforge.chat.ChatMod
import net.minecraftforge.common.ForgeVersion

object ChatConfig : Config(Mod(ChatMod.NAME, ModType.UTIL_QOL, "/assets/${ChatMod.ID}/icon.svg"), "${ChatMod.ID}.json") {
    init {
        initialize()

        hideIf("chatPosition", { ForgeVersion.mcVersion.equals("1.12.2") })
    }

    @Dropdown(
        name = "Text Shadow",
        description = "The type of text shadow.",
        options = ["No Shadow", "Shadow", "Full Shadow"],
        subcategory = "Text"
    )
    var textShadow = 1

    @Checkbox(
        name = "Background",
        description = "Toggles the background of the chat.",
        subcategory = "Background"
    )
    var background = true

    @Color(
        name = "Background Color",
        description = "The color of the chat's background.",
        subcategory = "Background"
    )
    var backgroundColor = OneColor(0, 0, 0, 128)

    @Switch(
        name = "Hide Blank Messages",
        description = "Hides messages that are blank.",
        subcategory = "Behavior"
    )
    var hideBlankMessages = false

    @Switch(
        name = "Modern Chat Position",
        description = "Moves chat above hearts like 1.12+",
        subcategory = "Behavior"
    )
    var chatPosition = false
}