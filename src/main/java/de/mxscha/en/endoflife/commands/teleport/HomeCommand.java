package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.home.Home;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if(args.length == 1) {
                Home home = EndoflifeCore.getInstance().getHomeManager().getHome(player, args[0]);
                if (home != null) {
                    player.sendMessage(Messages.PREFIX.get() + "§7Du wirst gleich Teleportiert...");
                    teleportToSpawn(player, home);
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast noch kein Home names §e" + args[0] + "§c!");
            } else {
                player.sendMessage(Messages.PREFIX.get() + "§cBitte benutze §e/home <Name>§c!");
            }
        }
        return false;
    }

    private void teleportToSpawn(Player player, Home home) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(home.getLocation());
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun bei deinem Zuhause!");
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20*3);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1){
            Player player = (Player) sender;
            return EndoflifeCore.getInstance().getHomeManager().getHomes(player).getList().stream().map(Home::getName).toList();
        }

        return new ArrayList<>();
    }
}
