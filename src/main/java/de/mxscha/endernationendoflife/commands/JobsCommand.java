package de.mxscha.endernationendoflife.commands;

import de.mxscha.endernationendoflife.utils.MessageManager;
import de.mxscha.endernationendoflife.utils.jobs.JobInventory;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class JobsCommand implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                new JobInventory(player);
            } else
                player.sendMessage(MessageManager.Prefix + "§cBenutze§8: §e/jobs§c!");
        }
        return false;
    }

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8» §6§lJobs")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §cSchließen")) {
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §a§lFarmer")) {
                // Farmer inventory
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §2§lHolzfäller")) {
                // Holzfäller inventory
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §d§lSchlachter")) {
                // Schlachter inventory
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §6§lMinenarbeiter")) {
                // Miner inventory
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §9§lFischer")) {
                // Fischer inventory
            }
        }
    }
}
