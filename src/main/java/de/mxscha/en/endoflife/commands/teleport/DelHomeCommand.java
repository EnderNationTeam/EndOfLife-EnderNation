package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.home.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DelHomeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                Home home = EndoflifeCore.getInstance().getHomeManager().getHome(player, args[0]);
                if (home != null) {
                    EndoflifeCore.getInstance().getHomeManager().delHome(player, args[0]);
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast dein Home gelöscht names §e" + args[0] + "§7!");
                } else {
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast dein Home bereits gelöscht!");
                }
            } else {
                player.sendMessage(Messages.PREFIX.get() + "§cBitte benutze §e/delhome <Name>§c!");
            }
        }
        return false;
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
