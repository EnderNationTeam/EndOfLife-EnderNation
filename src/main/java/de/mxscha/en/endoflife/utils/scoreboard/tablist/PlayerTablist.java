package de.mxscha.en.endoflife.utils.scoreboard.tablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerTablist {

    public static void setTablist(Player player) {
        setPlayerList(player);
        setAllPlayersTeam();
    }

    private static void setAllPlayersTeam() {
        Bukkit.getOnlinePlayers().forEach(PlayerTablist::setPlayerTeams);
    }

    private static void setPlayerTeams(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        Team owner = scoreboard.getTeam("00owner");
        Team manager = scoreboard.getTeam("01manager");
        Team admin = scoreboard.getTeam("02admin");
        Team developer = scoreboard.getTeam("03developer");
        Team srmoderator = scoreboard.getTeam("04srmoderator");
        Team moderator = scoreboard.getTeam("05moderator");
        Team supporter = scoreboard.getTeam("06supporter");
        Team builder = scoreboard.getTeam("07builder");
        Team youtuberplus = scoreboard.getTeam("08youtuberplus");
        Team youtuber = scoreboard.getTeam("09youtuber");
        Team enderhacker = scoreboard.getTeam("10enderhacker");
        Team enderhero = scoreboard.getTeam("11enderhero");
        Team enderking = scoreboard.getTeam("12enderking");
        Team players = scoreboard.getTeam("13player");

        if (owner == null) {
            owner = scoreboard.registerNewTeam("00owner");
        }
        if (manager == null) {
            manager = scoreboard.registerNewTeam("01manager");
        }
        if (admin == null) {
            admin = scoreboard.registerNewTeam("02admin");
        }
        if (developer == null) {
            developer = scoreboard.registerNewTeam("03developer");
        }
        if (srmoderator == null) {
            srmoderator = scoreboard.registerNewTeam("04srmoderator");
        }
        if (moderator == null) {
            moderator = scoreboard.registerNewTeam("05moderator");
        }
        if (supporter == null) {
            supporter = scoreboard.registerNewTeam("06supporter");
        }
        if (builder == null) {
            builder = scoreboard.registerNewTeam("07builder");
        }
        if (youtuberplus == null) {
            youtuberplus = scoreboard.registerNewTeam("08youtuberplus");
        }
        if (youtuber == null) {
            youtuber = scoreboard.registerNewTeam("09youtuber");
        }
        if (enderhacker == null) {
            enderhacker = scoreboard.registerNewTeam("10enderhacker");
        }
        if (enderhero == null) {
            enderhero = scoreboard.registerNewTeam("11enderhero");
        }
        if (enderking == null) {
            enderking = scoreboard.registerNewTeam("12enderking");
        }
        if (players == null) {
            players = scoreboard.registerNewTeam("13player");
        }

        owner.setPrefix("§4§lOwner §8» §7");
        manager.setPrefix("§c§lManager §8» §7");
        admin.setPrefix("§c§lAdmin §8» §7");
        developer.setPrefix("§b§lDeveloper §8» §7");
        srmoderator.setPrefix("§c§lSr§5§lModerator §8» §7");
        moderator.setPrefix("§5§lModerator §8» §7");
        supporter.setPrefix("§e§lSupporter §8» §7");
        builder.setPrefix("§1§lBuilder §8» §7");
        youtuberplus.setPrefix("§c§lYou§f§lTuber§d§l+ §8» §7");
        youtuber.setPrefix("§c§lYou§f§lTuber §8» §7");
        enderhacker.setPrefix("§3§lE§b§lHacker §8» §7");
        enderhero.setPrefix("§3§lE§5§lHero §8» §7");
        enderking.setPrefix("&3E§6§lKing §8» §7");
        players.setPrefix("§d§lBeta §8» §7");

        owner.setColor(ChatColor.GRAY);
        manager.setColor(ChatColor.GRAY);
        admin.setColor(ChatColor.GRAY);
        developer.setColor(ChatColor.GRAY);
        srmoderator.setColor(ChatColor.GRAY);
        moderator.setColor(ChatColor.GRAY);
        supporter.setColor(ChatColor.GRAY);
        builder.setColor(ChatColor.GRAY);
        youtuberplus.setColor(ChatColor.GRAY);
        youtuber.setColor(ChatColor.GRAY);
        enderhacker.setColor(ChatColor.GRAY);
        enderhero.setColor(ChatColor.GRAY);
        enderking.setColor(ChatColor.GRAY);
        players.setColor(ChatColor.GRAY);

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.hasPermission("rang.owner")) {
                owner.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.manager")) {
                manager.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.admin")) {
                admin.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.developer")) {
                developer.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.srmoderator")) {
                srmoderator.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.moderator")) {
                moderator.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.supporter")) {
                supporter.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.builder")) {
                builder.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.youtuberplus")) {
                youtuberplus.addEntry(target.getName());
                continue;
            } if (target.hasPermission("rang.youtuber")) {
                youtuber.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.enderhacker")) {
                enderhacker.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.enderhero")) {
                enderhero.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.enderking")) {
                enderking.addEntry(target.getName());
                continue;
            }
            if (target.hasPermission("rang.default") || target.hasPermission("rang.spieler")) {
                players.addEntry(target.getName());
                continue;
            }
            players.addEntry(target.getName());
        }
    }

    private static void setPlayerList(Player player) {
        player.setPlayerListHeader("");
        player.setPlayerListFooter("");
    }
}
