package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                try {
                    player.sendMessage(Messages.PREFIX.get() + "§7Du wirst gleich Teleportiert...");
                    teleportToSpawn(player);
                } catch (Exception e) {
                    player.sendMessage(Messages.PREFIX.get() + "§cDie Arena wurde nicht gesetzt!");
                }
            } else
                player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/arena§c!");
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
                    player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun in der Arena!");
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20*3);
    }
}