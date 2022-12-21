package de.mxscha.en.endoflife.commands.player;

import de.mxscha.en.endoflife.utils.scoreboard.manager.chat.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("endoflife.gamemode")) {
                switch (args.length) {
                    case 0:
                    case 1:
                        if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lSURVIVAL-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lCREATIVE-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                            player.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lADVENTURE-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lSPECTATOR-MODE§7!");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                        break;
                    case 2:
                        try {
                            Player target = Bukkit.getPlayer(args[1]);
                            if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                                target.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7ist nun im  §4§lSURVIVAL-MODE§7!");
                                target.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lSURVIVAL-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                            if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                                target.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7ist nun im §4§lCREATIVE-MODE§7!");
                                target.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lCREATIVE-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                            if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                                target.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7ist nun im §4§lADVENTURE-MODE§7!");
                                target.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lADVENTURE-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                            if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                                target.setGameMode(GameMode.SPECTATOR);
                                player.sendMessage(Messages.PREFIX.get() + "§e" + target.getName() + " §7ist nun im §4§lSPECTATOR-MODE§7!");
                                target.sendMessage(Messages.PREFIX.get() + "§7Du bist nun im §4§lSPECTATOR-MODE§7!");
                                target.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                        } catch (Exception e) {
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
