package de.mxscha.endernationendoflife.commands;

import de.mxscha.endernationendoflife.utils.MessageManager;
import de.mxscha.endernationendoflife.utils.inventory.InventoryOpener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class JobsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                InventoryOpener.open(player, 1);
            } else
                player.sendMessage(MessageManager.Prefix + "§cBenutze§8: §e/jobs§c!");
        }
        return false;
    }
}
