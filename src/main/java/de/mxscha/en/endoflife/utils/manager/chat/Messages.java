package de.mxscha.en.endoflife.utils.manager.chat;

import org.bukkit.ChatColor;

public enum Messages {

    OLD_PREFIX("&9&lEnd&f&lOf&a&lLife"),
    PPREFIX(TextColor.DARKER_BLUE.get() + "&lE" + TextColor.BLUE_2.get() + "&ln" + TextColor.BLUE_3.get() + "&ld" +
            TextColor.WHITE_BLUE.get() + "&lO" + TextColor.WHITE.get() + "&lf" + TextColor.WHITE_GREEN.get() + "&lL"  +
            TextColor.GREEN_2.get() + "&li" + TextColor.GREEN_3.get() + "&lf" + TextColor.DARKER_GREEN.get() + "&le"),
    PREFIX(PPREFIX.get() + " &8| &f"),
    NO_PERM(PREFIX.get() + "&cDazu hast du keine Rechte!"),
    PLS_USE(PREFIX.get() + "&cBitte Benutze&8: &f"),
    PLAYER_NOT_FOUND(PREFIX.get() + "&cDieser Spieler ist nicht Online!"),
    ALLOW_BUILD(PREFIX.get() + "&7Du darfst &anun &7bauen!"),
    DISALLOW_BUILD(PREFIX.get() + "&7Du darfst nun &cnicht mehr &7bauen!"),
    SET_VANISHED(PREFIX.get() + "&7Du bist nun Unsichtbar!"),
    SET_UNVANISHED(PREFIX.get() + "&7Du bist nun Sichtbar!");
    
    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String get() {
        return ChatColor.translateAlternateColorCodes('&', this.message);
    }
}
