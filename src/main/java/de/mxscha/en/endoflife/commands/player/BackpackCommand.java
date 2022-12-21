package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.scoreboard.manager.backpack.Backpack;
import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                Backpack backpack = EndoflifeCore.getInstance().getBackpackManager().getBackpack(player.getUniqueId());
                player.openInventory(backpack.getInventory());
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
            } else
            if(player.hasPermission("endoflife.backpack")) {
                if (args.length == 1) {
                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        Backpack backpack = EndoflifeCore.getInstance().getBackpackManager().getBackpack(target.getUniqueId());
                        player.openInventory(backpack.getInventory());
                    } catch (Exception e) {
                        player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                    }
                } else {
                    player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/backpack §e/ §8[§eSpielername§8]§c!");
                }
            } else
                player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/backpack§c!");
        }
        return false;
    }
}
