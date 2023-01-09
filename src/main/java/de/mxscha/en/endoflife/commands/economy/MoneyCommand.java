package de.mxscha.en.endoflife.commands.economy;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.money")) {
                if (args.length == 0) {
                    DecimalFormat f = new DecimalFormat("#0.00");
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast§8: §a" + f.format(EndoflifeCore.getInstance().getMoneyAPI().getMoney(player))+"€");
                } else {
                    if (args.length == 3) {
                        switch (args[0]) {
                            case "set" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().setMoney(target, Double.parseDouble(args[2]));
                                    DecimalFormat f = new DecimalFormat("#0.00");
                                    player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat nun §a" + f.format(EndoflifeCore.getInstance().getMoneyAPI().getMoney(target)) + "€§7!");
                                    new DefaultScoreboard(target).update();
                               } catch (Exception e) {
                                    player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                            case "add" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().addMoney(target, Double.parseDouble(args[2]));
                                    DecimalFormat f = new DecimalFormat("#0.00");
                                    player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat nun §a" + f.format(EndoflifeCore.getInstance().getMoneyAPI().getMoney(target)) + "€§7!");
                                    new DefaultScoreboard(target).update();
                                } catch (Exception e) {
                                    player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                            case "remove" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(target, Double.parseDouble(args[2]));
                                    DecimalFormat f = new DecimalFormat("#0.00");
                                    player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat nun §c" + f.format(EndoflifeCore.getInstance().getMoneyAPI().getMoney(target)) + "€§7!");
                                    new DefaultScoreboard(target).update();
                                } catch (Exception e) {
                                    player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                        }
                    } else if (args.length == 1) {
                        try {
                            Player target = Bukkit.getPlayer(args[0]);
                            player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat §a" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target) + "€§7!");
                        } catch (Exception e) {
                            player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                        }
                    }
                }
            } else {
                if (args.length == 0) {
                    DecimalFormat f = new DecimalFormat("#0.00");
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast§8: §a" + f.format(EndoflifeCore.getInstance().getMoneyAPI().getMoney(player))+"€");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/money§c!");
            }
        }
        return false;
    }
}
