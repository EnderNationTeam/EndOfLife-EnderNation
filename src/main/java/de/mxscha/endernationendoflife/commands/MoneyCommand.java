package de.mxscha.endernationendoflife.commands;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.money")) {
                if (args.length == 0) {
                    player.sendMessage(MessageManager.Prefix + "§7Du hast§8: §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId())+"€");
                } else {
                    if (args.length == 3) {
                        switch (args[0]) {
                            case "set" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().setMoney(target.getUniqueId(), Integer.parseInt(args[2]));
                                    player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7hat nun §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target.getUniqueId()) + "€§7!");
                                } catch (Exception e) {
                                    player.sendMessage(MessageManager.Prefix + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                            case "add" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().addMoney(target.getUniqueId(), Integer.parseInt(args[2]));
                                    player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7hat nun §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target.getUniqueId()) + "€§7!");
                                } catch (Exception e) {
                                    player.sendMessage(MessageManager.Prefix + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                            case "remove" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(target.getUniqueId(), Integer.parseInt(args[2]));
                                    player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7hat nun §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target.getUniqueId()) + "€§7!");
                                } catch (Exception e) {
                                    player.sendMessage(MessageManager.Prefix + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                        }
                    } else if (args.length == 1) {
                        try {
                            Player target = Bukkit.getPlayer(args[0]);
                            player.sendMessage(MessageManager.Prefix + "§e" + target.getName() + " §7hat §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target.getUniqueId()) + "€§7!");
                        } catch (Exception e) {
                            player.sendMessage(MessageManager.Prefix + "§cDieser Spieler ist nicht Online!");
                        }
                    }
                }
            } else {
                if (args.length == 0) {
                    player.sendMessage(MessageManager.Prefix + "§7Du hast§8: §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId())+"€");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cBenutze§8: §e/money§c!");
            }
        }
        return false;
    }
}
