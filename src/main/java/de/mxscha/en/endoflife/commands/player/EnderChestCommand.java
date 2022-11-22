package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.openInventory(player.getEnderChest());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1);
            } else
                if(player.hasPermission("endoflife.enderchest")) {
                    if (args.length == 1) {
                        try {
                            Player target = Bukkit.getPlayer(args[0]);
                            player.openInventory(target.getEnderChest());
                        } catch (Exception e) {
                            player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                        }
                    } else {
                        player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/enderchest §e/ §8[§eSpielername§8]§c!");
                    }
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/enderchest§c!");
        }
        return false;
    }
}