package de.mxscha.endernationendoflife.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String msg;
        if (player.hasPermission("chat.color")) {
            msg = event.getMessage().replace('&', '§');
        } else {
            msg = event.getMessage();
        }
        if (player.hasPermission("rang.owner")) {
            event.setFormat("§4§lOwner §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.manager")) {
            event.setFormat("§c§lManager §8» §7 §7" + player.getName() + "§8: §f" + msg);
        }  else
        if (player.hasPermission("rang.admin")) {
            event.setFormat("§c§lAdmin §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.developer")) {
            event.setFormat("§b§lDeveloper §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.srmoderator")) {
            event.setFormat("§c§lSr§5§lModerator §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.moderator")) {
            event.setFormat("§5§lModerator §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.supporter")) {
            event.setFormat("§e§lSupporter §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.builder")) {
            event.setFormat("§1§lBuilder §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.youtuberplus")) {
            event.setFormat("§c§lYou§f§lTuber§d§l+ §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.youtuber")) {
            event.setFormat("§c§lYou§f§lTuber §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.enderhacker")) {
            event.setFormat("§5§lEnder§b§lHacker §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.enderhero")) {
            event.setFormat("§5§lEnder§5§lHero §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.enderking")) {
            event.setFormat("&5Ender§6§lKing §8» §7" + player.getName() + "§8: §f" + msg);
        } else
        if (player.hasPermission("rang.default")) {
            event.setFormat("§7§lSpieler §8» §7" + player.getName() + "§8: §7" + msg);
        }
    }
}
