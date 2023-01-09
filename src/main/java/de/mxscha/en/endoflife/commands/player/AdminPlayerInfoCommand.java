package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class AdminPlayerInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.playerinfo")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/playerinfo §8[§eSpielername§8]");
                        break;
                    case 1:
                        try {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target.isOnline()) {
                                player.sendMessage(" ");
                                player.sendMessage("  §8» §eSpieler§8: §7" + target.getName());
                                switch (EndoflifeCore.getInstance().getJobAPI().getJob(target)) {
                                    case "Arbeitslos" ->
                                            player.sendMessage("  §8» §aJob§8: §8" + EndoflifeCore.getInstance().getJobAPI().getJob(target));
                                    case "Farmer" ->
                                            player.sendMessage("  §8» §aJob§8: §a" + EndoflifeCore.getInstance().getJobAPI().getJob(target));
                                    case "Holzfäller" ->
                                            player.sendMessage("  §8» §aJob§8: §2" + EndoflifeCore.getInstance().getJobAPI().getJob(target));
                                    case "Schlachter" ->
                                            player.sendMessage("  §8» §aJob§8: §d" + EndoflifeCore.getInstance().getJobAPI().getJob(target));
                                    case "Minenarbeiter" ->
                                            player.sendMessage("  §8» §aJob§8: §6" + EndoflifeCore.getInstance().getJobAPI().getJob(target));
                                    case "Fischer" ->
                                            player.sendMessage("  §8» §aJob§8: §9" + EndoflifeCore.getInstance().getJobAPI().getJob(target));
                                }
                                DecimalFormat f = new DecimalFormat("#0.00");
                                player.sendMessage("  §8» §cGeld§8: §7" + f.format(EndoflifeCore.getInstance().getMoneyAPI().getMoney(target)) + "€");
                                player.sendMessage(" ");
                            } else
                                player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                        } catch (Exception exception) {
                            player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                        }
                        break;
                }
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }
}
