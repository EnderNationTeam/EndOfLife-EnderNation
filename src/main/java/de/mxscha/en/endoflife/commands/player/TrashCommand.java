package de.mxscha.en.endoflife.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Inventory inventory = Bukkit.createInventory(null, 9*6 /* vielfaches von 9 */, "Mülleimer");;
            player.openInventory(inventory);
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
        }
        return false;
    }
}
