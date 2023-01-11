package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.home.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/*
 * Writer: Keksgauner
 * Time: 09.01.2023
 */
public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            int maxHomes = 3;

            for(int i = 500; i > 0; i--) {
                if(player.hasPermission("endoflife.home.max." + i)) {
                    maxHomes = i;
                    break;
                }
            }

            if(maxHomes == EndoflifeCore.getInstance().getHomeManager().getHomes(player).getList().size()) {
                player.sendMessage(Messages.PREFIX.get() + "§cDu hast bereits dein maximales Home Limit erreicht!");
                return false;
            }
            if (args.length == 0) {
                setHome(player, "home");
            } else if (args.length == 1) {
                setHome(player, args[0]);
            } else if (args.length == 2 && args[1].equalsIgnoreCase("confirm")) {
                    EndoflifeCore.getInstance().getHomeManager().setHome(player, args[0], player.getLocation());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast dein Home gesetzt");
                    player.sendMessage(Messages.PREFIX.get() + "§7Teleportiere dich mit §e/home " + args[0] + "§7!");
            } else {
                player.sendMessage(Messages.PREFIX.get() + "§cBitte benutze §e/sethome <Name>§c!");
            }
        }
        return false;
    }

    private void setHome(Player player, String name) {
        Home home = EndoflifeCore.getInstance().getHomeManager().getHome(player, name);
        if (home == null) {
            EndoflifeCore.getInstance().getHomeManager().setHome(player, name, player.getLocation());
            player.sendMessage(Messages.PREFIX.get() + "§7Du hast dein Home gesetzt");
            player.sendMessage(Messages.PREFIX.get() + "§7Teleportiere dich mit §e/home " + name + "§7!");
        } else {
            player.sendMessage(Messages.PREFIX.get() + "§cDu hast dein Home bereits gesetzt!");
            player.sendMessage(Messages.PREFIX.get() + "§cMit §e/sethome " + name + " confirm §ckann es überschrieben werden!");
        }
    }
}
