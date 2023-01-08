package de.mxscha.en.endoflife.commands.economy;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            switch (args.length) {
                case 0 -> {
                    player.sendMessage(Messages.PLS_USE.get() + "§e/pay §8[§espielername§8] §8[§cBetrag§8]");
                }
                case 1 -> {
                    player.sendMessage(Messages.PLS_USE.get() + "§e/pay §8[§espielername§8] §8[§cBetrag§8]");
                    break;
                }
                case 2 -> {
                    if (args[1].equalsIgnoreCase("all")) {
                            String playername = args[0];
                            double amount = EndoflifeCore.getInstance().getMoneyAPI().getMoney(player);
                            Player target = Bukkit.getPlayer(playername);
                            if (target == null) {
                                player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                            } else {
                                if (amount != 0) {
                                    if (amount <= EndoflifeCore.getInstance().getMoneyAPI().getMoney(player)) {
                                        EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player, amount);
                                        EndoflifeCore.getInstance().getMoneyAPI().addMoney(target, amount);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast §e" + target.getName() + " §c" + amount + "€ §7gezahlt!");
                                        target.sendMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7hat dir §c" + amount + "€ §7gezahlt!");
                                        new DefaultScoreboard(target).update();
                                        new DefaultScoreboard(player).update();
                                    } else
                                        player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
                                } else
                                    player.sendMessage(Messages.PREFIX.get() + "§cDu kannst nicht 0€ senden!");
                            }
                    } else {
                        try {
                            String playername = args[0];
                            double amount = Double.parseDouble(args[1]);
                            Player target = Bukkit.getPlayer(playername);
                            if (target == null) {
                                player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                            } else {
                                if (amount != 0) {
                                    if (amount <= EndoflifeCore.getInstance().getMoneyAPI().getMoney(player)) {
                                        EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player, amount);
                                        EndoflifeCore.getInstance().getMoneyAPI().addMoney(target, amount);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast §e" + target.getName() + " §c" + amount + "€ §7gezahlt!");
                                        target.sendMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7hat dir §c" + amount + "€ §7gezahlt!");
                                        new DefaultScoreboard(target).update();
                                        new DefaultScoreboard(player).update();
                                    } else
                                        player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
                                } else
                                    player.sendMessage(Messages.PREFIX.get() + "§cDu kannst nicht 0€ senden!");
                            }
                        } catch (NumberFormatException exception) {
                            player.sendMessage(Messages.PREFIX.get() +  "§cDer Betrag muss eine Zahl sein!");
                        }
                    }
                }
            }
        }
        return false;
    }
}
