package de.mxscha.endernationendoflife.commands;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.MessageManager;
import de.mxscha.endernationendoflife.utils.locations.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SpawnCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                try {
                    player.sendMessage(MessageManager.Prefix + "§7Du wirst gleich Teleportiert...");
                    teleportToSpawn(player);
                } catch (Exception e) {
                    player.sendMessage(MessageManager.Prefix + "§cDer Spawn wurde nicht gesetzt!");
                }
            } else
                player.sendMessage(MessageManager.Prefix + "§cBenutze§8: §e/spawn§c!");
        }
        return false;
    }

    private void teleportToSpawn(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                    Location spawn = new ConfigLocationUtil("Spawn").loadLocation();
                    player.teleport(spawn);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    player.sendMessage(MessageManager.Prefix + "§7Du bist nun am Spawn!");
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20*3);
    }
}