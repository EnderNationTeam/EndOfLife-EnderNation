package de.mxscha.en.endoflife.utils.scoreboard.manager.job;

import org.bukkit.ChatColor;

public enum Job {

    FARMER("Farmer"),
    LUMBERJACK("Holzfäller"),
    BUTCHER("Schlachter"),
    MINER("Minenarbeiter"),
    FISHER("Fischer"),
    UNEMPLOYED("Arbeitslos");

    private Job(String translation) {
        this.translation = translation;
    }

    private final String translation;

    public String getTranslation() {
        return translation;
    }
}
