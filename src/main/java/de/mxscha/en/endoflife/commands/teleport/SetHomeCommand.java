package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                if (EndoflifeCore.getInstance().getHomeManager().getHome(player) == null) {
                    EndoflifeCore.getInstance().getHomeManager().setHome(player, player.getLocation());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast dein Home gesetzt");
                    player.sendMessage(Messages.PREFIX.get() + "§7Teleportiere dich mit §e/home§7!");
                } else {
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast dein Home bereits gesetzt!");
                    player.sendMessage(Messages.PREFIX.get() + "§cMit §e/sethome confirm §ckann es überschrieben werden!");
                }
            } else if (args.length == 1) {
                if (args[0].toLowerCase().equals("confirm")) {
                    EndoflifeCore.getInstance().getHomeManager().setHome(player, player.getLocation());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast dein Home gesetzt");
                    player.sendMessage(Messages.PREFIX.get() + "§7Teleportiere dich mit §e/home§7!");
                }
            }
        }
        return false;
    }
}
