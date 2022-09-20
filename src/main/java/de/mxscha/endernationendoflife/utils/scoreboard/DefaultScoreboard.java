package de.mxscha.endernationendoflife.utils.scoreboard;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.scoreboard.tablist.TablistManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DefaultScoreboard extends ScoreboardBuilder {

    private int id;

    public DefaultScoreboard(Player player) {
        super(player, "       §9§lEnd§f§lOf§a§lLife         ");
        this.id = 0;
        TablistManager.setTablist(player);
    }

    public void createScoreboard() {
        setScore("§8§m                               ", 10);
        setScore("§8● §7Dein Rang§8:", 9);
        if (player.hasPermission("rang.owner")) {
            setScore("  §8» §4§lOwner", 8);
        } else if (player.hasPermission("rang.manager")) {
            setScore("  §8» §c§lManager", 8);
        } else if (player.hasPermission("rang.admin")) {
            setScore("  §8» §c§lAdmin", 8);
        } else if (player.hasPermission("rang.developer")) {
            setScore("  §8» §b§lDeveloper", 8);
        } else if (player.hasPermission("rang.srmoderator")) {
            setScore("  §8» §c§lSr§5§lModerator", 8);
        } else if (player.hasPermission("rang.moderator")) {
            setScore("  §8» §5§lModerator", 8);
        } else if (player.hasPermission("rang.supporter")) {
            setScore("  §8» §e§lSupporter", 8);
        } else if (player.hasPermission("rang.builder")) {
            setScore("  §8» §1§lBuilder", 8);
        } else if (player.hasPermission("rang.youtuberplus")) {
            setScore("  §8» §5§lYou§f§lTuber§d§l+", 8);
        } else if (player.hasPermission("rang.youtuber")) {
            setScore("  §8» §5§lYou§f§lTuber", 8);
        } else if (player.hasPermission("rang.enderhacker")) {
            setScore("  §8» §5§lEnder§b§lHacker", 8);
        } else if (player.hasPermission("rang.enderhero")) {
            setScore("  §8» §5§lEnder§5§lHero", 8);
        } else if (player.hasPermission("rang.enderking")) {
            setScore("  §8» &5Ender§6§lKing", 8);
        } else if (player.hasPermission("rang.spieler") || player.hasPermission("rang.default")) {
            setScore("  §8» §7§lSpieler", 8);
        }
        setScore("§a", 7);
        setScore("§8● §7Dein Geld§8:", 6);
        setScore("  §8» §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) + "€", 5);
        setScore("§b", 4);
        setScore("§8● §7Dein Job§8:", 3);
        switch (EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId())) {
            case "Arbeitslos" ->
                    setScore("  §8» §8§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Farmer" ->
                    setScore("  §8» §a§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Holzfäller" ->
                    setScore("  §8» §2§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Schlachter" ->
                    setScore("  §8» §d§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Miner" ->
                    setScore("  §8» §6§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Fischer" ->
                    setScore("  §8» §9§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
        }
        setScore("§a§8§m                               ", 1);
    }

    public void update() {
        setScore("§a", 7);
        setScore("§8● §7Dein Geld§8:", 6);
        setScore("  §8» §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) + "€", 5);
        setScore("§b", 4);
        setScore("§8● §7Dein Job§8:", 3);
        switch (EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId())) {
            case "Arbeitslos" ->
                    setScore("  §8» §8§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Farmer" ->
                    setScore("  §8» §a§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Holzfäller" ->
                    setScore("  §8» §2§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Schlachter" ->
                    setScore("  §8» §d§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Minenarbeiter" ->
                    setScore("  §8» §6§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
            case "Fischer" ->
                    setScore("  §8» §9§l" + EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()), 2);
        }
        setScore("§a§8§m                               ", 1);

    }
}
