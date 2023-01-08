package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveHeadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.headgive")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage(Messages.PLS_USE.get() + "§e/head §8[§espielername§8]");
                        break;
                    case 1:
                        String targetName = args[0];
                        player.getInventory().addItem(new ItemCreator(Material.PLAYER_HEAD).setSkull("§9Kopf von " + targetName, targetName).toItemStack());
                        break;
                }
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }

}
