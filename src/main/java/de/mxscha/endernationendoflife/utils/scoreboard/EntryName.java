package de.mxscha.endernationendoflife.utils.scoreboard;

import org.bukkit.ChatColor;

public enum EntryName {
    ENTRY_0(0, ChatColor.DARK_PURPLE.toString()),
    ENTRY_1(1, ChatColor.AQUA.toString()),
    ENTRY_2(2, ChatColor.DARK_BLUE.toString()),
    ENTRY_3(3, ChatColor.DARK_GREEN.toString()),
    ENTRY_4(4, ChatColor.DARK_RED.toString()),
    ENTRY_5(5, ChatColor.DARK_AQUA.toString()),
    ENTRY_6(6, ChatColor.RED.toString()),
    ENTRY_7(7, ChatColor.BLUE.toString()),
    ENTRY_8(8, ChatColor.YELLOW.toString()),
    ENTRY_9(9, ChatColor.GREEN.toString()),
    ENTRY_10(10, ChatColor.WHITE.toString()),
    ENTRY_11(11, ChatColor.GOLD.toString()),
    ENTRY_12(12, ChatColor.GRAY.toString()),
    ENTRY_13(13, ChatColor.DARK_GRAY.toString()),
    ENTRY_14(14, ChatColor.ITALIC.toString()),
    ENTRY_15(15, ChatColor.STRIKETHROUGH.toString()),
    ENTRY_16(16, ChatColor.MAGIC.toString());

    private final int entry;

    private final String entryName;

    EntryName(int entry, String entryName) {
        this.entry = entry;
        this.entryName = entryName;
    }

    public String getEntryName() {
        return this.entryName;
    }

    public int getEntry() {
        return this.entry;
    }
}
