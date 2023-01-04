package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.invsee")) {
                if (args.length == 0) {
                    player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/invsee §8[§eSpielername§8]");
                } else {
                    if (args.length != 1) {
                        player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/invsee §8[§eSpielername§8]");
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != player) {
                                player.openInventory(target.getInventory());
                            } else
                                player.sendMessage(Messages.PREFIX.get() + "§cDu darfst dein eigenes Inventar nicht einsehen!");
                        } catch (Exception e) {
                            player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                        }
                    }
                }
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }
}
