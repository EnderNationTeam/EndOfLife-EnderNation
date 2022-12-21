package de.mxscha.en.endoflife.commands.teleport;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class TpacceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            switch (args.length) {
                case 0:
                    player.sendMessage(Messages.PLS_USE.get() + "§e/tpaccept §8[§espielername§8]");
                    break;
                case 1:
                    String targetsName = args[0];
                    try {
                        Player target = Bukkit.getPlayer(targetsName);
                        if (target.isOnline()) {
                            if (TpaCommand.getRequest().containsValue(player)) {
                                if (TpaCommand.getRequest().containsKey(target)) {
                                    TpaCommand.getRequest().remove(target);
                                    target.sendMessage(Messages.PREFIX.get() + "§e" + player.getName() + "§7 hat deine Teleportanfrage angenommen!");
                                    target.sendMessage(Messages.PREFIX.get() + "§7Du wirst in 3 Sekunden Teleportiert...");
                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die Teleportanfrage von §e" + target.getName() + "§a angenommen!");
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            TpaCommand.getTpaSave().add(player);
                                            target.teleport(player);
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    TpaCommand.getTpaSave().remove(player);
                                                }
                                            }.runTaskLater(EndoflifeCore.getInstance(), 20*15);
                                        }
                                    }.runTaskLater(EndoflifeCore.getInstance(), 20*3);
                                } else
                                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast keine Teleport von diesem Spieler erhalten");
                            } else
                                player.sendMessage(Messages.PREFIX.get() + "§cDu hast keine Anfrage erhalten");
                        } else
                            player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                    } catch (Exception exception) {
                        player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                    }
                    break;
            }
        }
        return false;
    }
}