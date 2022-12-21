package de.mxscha.en.endoflife.commands.economy;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
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
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast§8: §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(player)+"€");
                } else {
                    if (args.length == 3) {
                        switch (args[0]) {
                            case "set" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().setMoney(target, Integer.parseInt(args[2]));
                                    player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat nun §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target) + "€§7!");
                                    new DefaultScoreboard(target).update();
                                    new DefaultScoreboard(player).update();
                               } catch (Exception e) {
                                    player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                            case "add" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().addMoney(target, Integer.parseInt(args[2]));
                                    player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat nun §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target) + "€§7!");
                                    new DefaultScoreboard(target).update();
                                    new DefaultScoreboard(player).update();
                                } catch (Exception e) {
                                    player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                            case "remove" -> {
                                try {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(target, Integer.parseInt(args[2]));
                                    player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat nun §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target) + "€§7!");
                                    new DefaultScoreboard(target).update();
                                    new DefaultScoreboard(player).update();
                                } catch (Exception e) {
                                    player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                                }
                            }
                        }
                    } else if (args.length == 1) {
                        try {
                            Player target = Bukkit.getPlayer(args[0]);
                            player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7hat §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(target) + "€§7!");
                        } catch (Exception e) {
                            player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht Online!");
                        }
                    }
                }
            } else {
                if (args.length == 0) {
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast§8: §c" + EndoflifeCore.getInstance().getMoneyAPI().getMoney(player)+"€");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cBenutze§8: §e/money§c!");
            }
        }
        return false;
    }
}
