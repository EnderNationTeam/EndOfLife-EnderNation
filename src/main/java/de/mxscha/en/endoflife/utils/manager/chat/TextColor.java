package de.mxscha.en.endoflife.utils.manager.chat;

import net.md_5.bungee.api.ChatColor;

public enum TextColor {

    DARKER_BLUE(ChatColor.of("#0D2CA6")),
    BLUE_2(ChatColor.of("#113BDF")),
    BLUE_3(ChatColor.of("#0738FB")),
    WHITE_BLUE(ChatColor.of("#C1CBF4")),
    WHITE(ChatColor.of("#FFFFFF")),
    WHITE_GREEN(ChatColor.of("#BAFADE")),
    GREEN_2(ChatColor.of("#48E4A0")),
    GREEN_3(ChatColor.of("#2DD58C")),
    TPA_1(ChatColor.of("#98358F")),
    TPA_2(ChatColor.of("#A62C9B")),
    TPA_3(ChatColor.of("#AF2CA3")),
    DARKER_GREEN(ChatColor.of("#0FBE72"));

    private ChatColor color;

    TextColor(ChatColor color) {
        this.color = color;
    }

    public ChatColor get() {
        return color;
    }
}
