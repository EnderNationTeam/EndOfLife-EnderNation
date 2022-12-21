package de.mxscha.en.endoflife.commands.economy;

import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.scoreboard.manager.item.inventory.InventoryOpener;
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
                player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/jobs§c!");
        }
        return false;
    }
}
