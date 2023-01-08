package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (EndoflifeCore.getInstance().getHomeManager().getHome(player) != null) {
                player.sendMessage(Messages.PREFIX.get() + "§7Du wirst gleich Teleportiert...");
                teleportToSpawn(player);
            } else
                player.sendMessage(Messages.PREFIX.get() + "§cDu hast noch kein Home");
        }
        return false;
    }

    private void teleportToSpawn(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location home = EndoflifeCore.getInstance().getHomeManager().getHome(player);
                player.teleport(home);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun bei deinem Zuhause!");
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20*3);
    }
}
