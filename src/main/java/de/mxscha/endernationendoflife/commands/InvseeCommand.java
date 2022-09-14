package de.mxscha.endernationendoflife.commands;

import de.mxscha.endernationendoflife.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Member;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.invsee")) {
                if (args.length == 0) {
                    player.sendMessage(MessageManager.Prefix + "§cBenutze§8: §e/invsee §8[§eSpielername§8]");
                } else {
                    if (args.length != 1) {
                        player.sendMessage(MessageManager.Prefix + "§cBenutze§8: §e/invsee §8[§eSpielername§8]");
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != player) {
                                player.openInventory(target.getInventory());
                            } else
                                player.sendMessage(MessageManager.Prefix + "§cDu darfst dein eigenes Inventar nicht einsehen!");
                        } catch (Exception e) {
                            player.sendMessage(MessageManager.Prefix + "§cDieser Spieler ist nicht Online!");
                        }
                    }
                }
            } else
                player.sendMessage(MessageManager.NoPerm);
        }
        return false;
    }
}
