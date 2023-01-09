package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.home.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            int maxHomes = 3;

            for(int i = 500; i > 0; i--) {
                if(player.hasPermission("endoflife.sethome.max." + i)) {
                    maxHomes = i;
                    break;
                }
            }

            /*
            if(player.hasPermission("endoflife.sethome.max.5")) maxHomes = 5;
            if(player.hasPermission("endoflife.sethome.max.10")) maxHomes = 10;
            if(player.hasPermission("endoflife.sethome.max.15")) maxHomes = 15;
             */

            if(maxHomes == EndoflifeCore.getInstance().getHomeManager().getHomes(player).getList().size()) {
                player.sendMessage(Messages.PREFIX.get() + "§cDu hast bereits dein maximales Home Limit erreicht!");
                return false;
            }

            if (args.length == 1) {
                Home home = EndoflifeCore.getInstance().getHomeManager().getHome(player, args[0]);
                if (home == null) {
                    EndoflifeCore.getInstance().getHomeManager().setHome(player, args[0], player.getLocation());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast dein Home gesetzt");
                    player.sendMessage(Messages.PREFIX.get() + "§7Teleportiere dich mit §e/home " + args[0] + "§7!");
                } else {
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast dein Home bereits gesetzt!");
                    player.sendMessage(Messages.PREFIX.get() + "§cMit §e/sethome " + args[0] + " confirm §ckann es überschrieben werden!");
                }
            } else if (args.length == 2 && args[1].equalsIgnoreCase("confirm")) {
                    EndoflifeCore.getInstance().getHomeManager().setHome(player, args[0], player.getLocation());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast dein Home gesetzt");
                    player.sendMessage(Messages.PREFIX.get() + "§7Teleportiere dich mit §e/home " + args[0] + "§7!");
            } else {
                player.sendMessage(Messages.PREFIX.get() + "§cBitte benutze §e/sethome <Name>§c!");
            }
        }
        return false;
    }
}
