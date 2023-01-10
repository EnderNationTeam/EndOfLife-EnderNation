package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.home.Home;
import de.mxscha.en.endoflife.utils.manager.teleport.Teleport;
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
            if(args.length == 0) {
                home(player, "home");
            } else if(args.length == 1) {
                home(player, args[0]);
            } else {
                player.sendMessage(Messages.PREFIX.get() + "§cBitte benutze §e/home <Name>§c!");
            }
        }
        return false;
    }

    private void home(Player player, String name) {
        Home home = EndoflifeCore.getInstance().getHomeManager().getHome(player, name);
        if (home != null) {
            Teleport teleport = new Teleport(player, home.getLocation()).setSound(Sound.ENTITY_PLAYER_LEVELUP).setTeleportTime(3);
            teleport.setBeforeMessage(Messages.PREFIX.get() + "§7Du wirst in §e" + teleport.getTeleportTime() + " Sekunden §7teleportiert! §cBitte bewege dich nicht!");
            teleport.setAfterMessage(Messages.PREFIX.get() + "§7Du wurdest zu deinem Home §e" + name + " §7teleportiert!");
            teleport.teleport();
        } else
            player.sendMessage(Messages.PREFIX.get() + "§cDu hast noch kein Home names §e" + name + "§c!");
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
