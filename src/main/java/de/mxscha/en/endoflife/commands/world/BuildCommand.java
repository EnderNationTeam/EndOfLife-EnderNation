package de.mxscha.en.endoflife.commands.world;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BuildCommand implements CommandExecutor {

    private static final ArrayList<Player> build = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            switch (args.length) {
                case 0:
                    player.sendMessage(Messages.PLS_USE.get() + "§e/build §aan§7/§caus §7| §8[§aSpieler§8] §aan§7/§caus§7!");
                    break;
                case 1:
                    String arg0 = args[0];
                    if (arg0.equalsIgnoreCase("an")) {
                        if (build.contains(player)) {
                            player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist bereits im Build Modus!");
                        } else {
                            build.add(player);
                            player.sendMessage(Messages.ALLOW_BUILD.get());
                        }
                    } else if (arg0.equalsIgnoreCase("aus")) {
                        if (!build.contains(player)) {
                            player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht im Build Modus!");
                        } else {
                            build.remove(player);
                            player.sendMessage(Messages.DISALLOW_BUILD.get());
                        }
                    }
                    break;
                case 2:
                    String playername = args[0];
                    Player target = Bukkit.getPlayer(playername);
                    if (target != null) {
                        String arg1 = args[1];
                        if (arg1.equalsIgnoreCase("an")) {
                            if (build.contains(target)) {
                                player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist bereits im Build Modus!");
                            } else {
                                build.add(target);
                                target.sendMessage(Messages.ALLOW_BUILD.get());
                                player.sendMessage(Messages.PREFIX.get() + "§a" + target.getName() + "§7 darf §anun §7bauen!");
                            }
                        } else if (arg1.equalsIgnoreCase("aus")) {
                            if (!build.contains(target)) {
                                player.sendMessage(Messages.PREFIX.get() + "§cDieser Spieler ist nicht im Build Modus!");
                            } else {
                                build.remove(target);
                                target.sendMessage(Messages.DISALLOW_BUILD.get());
                                player.sendMessage(Messages.PREFIX.get() + "§a" + target.getName() + "§7 darf nun §cnicht mehr §7bauen!");
                            }
                        }
                    } else
                        player.sendMessage(Messages.PLAYER_NOT_FOUND.get());
                    break;
            }
        }
        return false;
    }

    public static ArrayList<Player> getBuild() {
        return build;
    }
}
